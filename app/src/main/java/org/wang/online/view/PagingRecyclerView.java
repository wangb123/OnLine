package org.wang.online.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import org.wang.online.R;

/**
 * 可分页的RecyclerView，同时可设置EmptyView，
 * Created by 王冰 on 2016/11/29.
 */

public class PagingRecyclerView<T> extends RecyclerView {
    private int page;
    private boolean hasMore;
    private View emptyLayout;

    public PagingRecyclerView(Context context) {
        super(context);
    }

    public PagingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttr(attrs);

    }

    public PagingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getAttr(attrs);
    }

    private void getAttr(@Nullable AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.PagingRecyclerView);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.PagingRecyclerView_empty_layout:
                    emptyLayout = View.inflate(getContext(), a.getResourceId(attr, R.layout.leak_canary_heap_dump_toast), this);
                    break;
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }

}
