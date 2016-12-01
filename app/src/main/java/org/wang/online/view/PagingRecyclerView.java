package org.wang.online.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import org.wang.online.R;
import org.wang.online.util.LogUtils;

import static org.wang.online.view.PagingRecyclerView.State.Load;
import static org.wang.online.view.PagingRecyclerView.State.Loading;

/**
 * 可分页的RecyclerView，同时可设置EmptyView，
 * Created by 王冰 on 2016/11/29.
 */

public class PagingRecyclerView extends RecyclerView {
    private int page;
    private int emptyLayout;
    private State state = Loading;
    private OnLoadMoreListener loadMoreListener;
    private OnScrollListener onScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LogUtils.e(newState + "");
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (state != Load || loadMoreListener == null) return;
            LayoutManager manager = getLayoutManager();
            if (manager == null) return;
            int count = manager.getItemCount();
            int last = 0;
            count--;
            if (manager instanceof LinearLayoutManager) {
                last = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
            } else if (manager instanceof StaggeredGridLayoutManager) {
                LogUtils.e("2");
            }
            if (count == last) {
                loadMoreListener.onLoadMore(page++);
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
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
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
        if (state != Load)
            notifyStateChanged();
    }

    private void notifyStateChanged() {
        switch (this.state) {
            case Refresh:
                loadMoreListener.onLoadMore(page = 1);
                setState(Load);
                break;
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int page);
    }

    public enum State {
        Load, Loading, LoadFail, Loaded, Refresh
    }
}
