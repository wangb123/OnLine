package org.wang.online.ui.main;

import android.content.Context;
import android.databinding.Bindable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.wang.online.BR;
import org.wang.online.config.Callback;
import org.wang.online.config.Error;
import org.wang.online.ui.ViewModel;

/**
 * Created by 王冰 on 2016/11/29.
 */

public class RecommendViewModel extends ViewModel {
    private Context context;
    @Bindable
    private boolean refreshing;
    private RecommendAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private SwipeRefreshLayout.OnRefreshListener listener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            setRefreshing(true);
            RecommendModel.get(callback);
        }
    };
    private Callback.SimpleCallback<RecommendModel> callback = new Callback.SimpleCallback<RecommendModel>() {
        @Override
        public void onSuccess(RecommendModel recommendModel) {
            adapter.setModel(recommendModel);
        }

        @Override
        public void onFail(Error error) {
            error.printStackTrace();
        }

        @Override
        public void onAfter() {
            super.onAfter();
            setRefreshing(false);
        }
    };

    public RecommendViewModel(Context context) {
        this.context = context;
        manager = new LinearLayoutManager(context);
        adapter = new RecommendAdapter();
        listener.onRefresh();
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    public void setRefreshing(boolean refreshing) {
        this.refreshing = refreshing;
        notifyPropertyChanged(BR.refreshing);
    }

    public SwipeRefreshLayout.OnRefreshListener getListener() {
        return listener;
    }

    public void setListener(SwipeRefreshLayout.OnRefreshListener listener) {
        this.listener = listener;
    }

    public RecommendAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecommendAdapter adapter) {
        this.adapter = adapter;
    }

    public RecyclerView.LayoutManager getManager() {
        return manager;
    }

    public void setManager(RecyclerView.LayoutManager manager) {
        this.manager = manager;
    }
}
