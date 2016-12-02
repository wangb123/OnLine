package org.wang.online.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.wang.online.R;
import org.wang.online.util.LogUtils;

import static org.wang.online.view.PagingRecyclerView.State.Load;
import static org.wang.online.view.PagingRecyclerView.State.LoadFail;
import static org.wang.online.view.PagingRecyclerView.State.Loading;

/**
 * 可分页的RecyclerView，同时可设置EmptyView，
 * Created by 王冰 on 2016/11/29.
 */

public class PagingRecyclerView extends RecyclerView {
    private int page = 1;
    private int emptyLayout;
    private int bottomLayout;
    private Adapter adapter;
    private State state = Loading;
    private OnLoadMoreListener loadMoreListener;
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            loadMore(page);
        }
    };
    private OnScrollListener onScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (state != Load || loadMoreListener == null) return;
            LayoutManager manager = getLayoutManager();
            if (manager == null) return;
            int count = adapter.getItemCount();
            int last = 0;
            if (manager instanceof LinearLayoutManager) {
                last = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
            } else if (manager instanceof StaggeredGridLayoutManager) {
                int[] lasts;
                lasts = ((StaggeredGridLayoutManager) manager).findLastVisibleItemPositions(null);
                for (int i : lasts) {
                    if (last < i) last = i;
                }
            }
            LogUtils.e(count + "  " + last);
            if (count == last) {
                loadMore(page);
            }
        }
    };

    public PagingRecyclerView(Context context) {
        super(context);
        addOnScrollListener(onScrollListener);
    }

    public PagingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttr(attrs);
        addOnScrollListener(onScrollListener);
    }

    public PagingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getAttr(attrs);
        addOnScrollListener(onScrollListener);
    }

    private void getAttr(@Nullable AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.PagingRecyclerView);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.PagingRecyclerView_emptyLayout:
                    emptyLayout = a.getResourceId(attr, R.layout.empty_view);
                    break;
                case R.styleable.PagingRecyclerView_bottomLayout:
                    bottomLayout = a.getResourceId(attr, R.layout.load_view);
                    break;
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        super.setAdapter(new BottomAdapter(adapter));
        loadMore(page);
    }

    @Override
    public Adapter getAdapter() {
        return adapter;
    }

    public OnLoadMoreListener getLoadMoreListener() {
        return loadMoreListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if (this.state == state) return;
        this.state = state;
        notifyStateChanged();
    }

    private void notifyStateChanged() {
        switch (this.state) {
            case Load:
                page++;
                LogUtils.e("下一页：" + page);
                break;
            case Loading:
                LogUtils.e("正在加载：" + page);
                break;
            case LoadFail:
                LogUtils.e("加载失败：" + page);
                break;
            case Loaded:
                LogUtils.e("最后一页：" + page);
                break;
            case Refresh:
                loadMore(page = 1);
                break;
        }
        super.getAdapter().notifyItemChanged(super.getAdapter().getItemCount() - 1);
    }

    private void loadMore(int page) {
        setState(Loading);
        if (loadMoreListener != null)
            loadMoreListener.onLoadMore(page);

    }


    public interface OnLoadMoreListener {
        void onLoadMore(int page);
    }

    public enum State {
        Load, Loading, LoadFail, Loaded, Refresh
    }

    class BottomAdapter extends WrapperAdapter {
        private final int Empty = Integer.MIN_VALUE;
        private final int Bottom = Integer.MIN_VALUE + 1;

        BottomAdapter(Adapter wrapped) {
            super(wrapped);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case Empty:
                    return createBottomLayout(parent, emptyLayout);
                case Bottom:
                    return createBottomLayout(parent, bottomLayout);
                default:
                    return super.onCreateViewHolder(parent, viewType);
            }
        }

        private ViewHolder createBottomLayout(ViewGroup parent, int layout) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(layout, parent, false);
            view.setOnClickListener(onClickListener);
            return new ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case Empty:
                    bindEmpty(holder);
                    break;
                case Bottom:
                    bindBottom(holder);
                    break;
                default:
                    super.onBindViewHolder(holder, position);
                    break;
            }
        }

        private void bindEmpty(ViewHolder holder) {
            holder.itemView.setEnabled(state == LoadFail);
            String str = "";
            TextView hint = (TextView) holder.itemView.findViewById(R.id.hint);
            switch (state) {
                case Load:
                    str = "加载完成";
                    break;
                case Loading:
                    str = "正在加载中";
                    break;
                case LoadFail:
                    str = "加载失败,点击重试";
                    break;
                case Loaded:
                    str = "没有更多了";
                    break;
                case Refresh:
                    str = "正在刷新";
                    break;
            }
            hint.setText(str);

        }

        private void bindBottom(ViewHolder holder) {
            String str = "";
            TextView hint = (TextView) holder.itemView.findViewById(R.id.hint);
            switch (state) {
                case Load:
                    str = "加载完成";
                    break;
                case Loading:
                    str = "正在加载中";
                    break;
                case LoadFail:
                    str = "加载失败,点击重试";
                    break;
                case Loaded:
                    str = "没有更多了";
                    break;
                case Refresh:
                    str = "正在刷新";
                    break;
            }
            hint.setText(str);
            hint.setEnabled(state == LoadFail);
        }

        @Override
        public int getItemCount() {
            return super.getItemCount() == 0 && emptyLayout != 0 ? 1 : super.getItemCount() + (bottomLayout == 0 ? 0 : 1);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemCount() == 0 && emptyLayout != 0 ? Empty : position < super.getItemCount() ? super.getItemViewType(position) : Bottom;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            setGridHeaderFooter(recyclerView.getLayoutManager());
        }

        private void setGridHeaderFooter(LayoutManager layoutManager) {
            if (getItemCount() == super.getItemCount()) return;
            if (layoutManager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        boolean isShowFooter = (position == getItemCount() - 1);
                        if (isShowFooter) {
                            return gridLayoutManager.getSpanCount();
                        }
                        return 1;
                    }
                });
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            }
        }
    }

    class WrapperAdapter extends RecyclerView.Adapter {

        private final RecyclerView.Adapter wrapped;

        WrapperAdapter(Adapter wrapped) {
            super();
            this.wrapped = wrapped;
            this.wrapped.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                public void onChanged() {
                    notifyDataSetChanged();
                }

                public void onItemRangeChanged(int positionStart, int itemCount) {
                    notifyItemRangeChanged(positionStart, itemCount);
                }

                public void onItemRangeInserted(int positionStart, int itemCount) {
                    notifyItemRangeInserted(positionStart, itemCount);
                }

                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    notifyItemRangeRemoved(positionStart, itemCount);
                }

                public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                    notifyItemMoved(fromPosition, toPosition);
                }
            });
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return wrapped.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            wrapped.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return wrapped.getItemCount();
        }

        @Override
        public int getItemViewType(int position) {
            return wrapped.getItemViewType(position);
        }

        @Override
        public void setHasStableIds(boolean hasStableIds) {
            wrapped.setHasStableIds(hasStableIds);
        }

        @Override
        public long getItemId(int position) {
            return wrapped.getItemId(position);
        }

        @Override
        public void onViewRecycled(RecyclerView.ViewHolder holder) {
            wrapped.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
            return wrapped.onFailedToRecycleView(holder);
        }

        @Override
        public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
            wrapped.onViewAttachedToWindow(holder);
        }

        @Override
        public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
            wrapped.onViewDetachedFromWindow(holder);
        }

        @Override
        public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
            wrapped.registerAdapterDataObserver(observer);
        }

        @Override
        public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
            wrapped.unregisterAdapterDataObserver(observer);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            wrapped.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            wrapped.onDetachedFromRecyclerView(recyclerView);
        }

        public RecyclerView.Adapter getWrappedAdapter() {
            return wrapped;
        }
    }
}
