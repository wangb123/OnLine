package org.wang.online.ui.main;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.wang.online.config.Callback;
import org.wang.online.config.Error;
import org.wang.online.ui.ViewModel;
import org.wang.online.util.LogUtils;

/**
 * Created by 王冰 on 2016/11/29.
 */

public class RecommendViewModel extends ViewModel {
    private Context context;
    private RecommendAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private Callback.SimpleCallback<RecommendModel> callback = new Callback.SimpleCallback<RecommendModel>() {
        @Override
        public void onSuccess(RecommendModel recommendModel) {
            LogUtils.e(recommendModel.toString());
            adapter.setModel(recommendModel);
        }

        @Override
        public void onFail(Error error) {
            error.printStackTrace();
        }
    };

    public RecommendViewModel(Context context) {
        this.context = context;
        manager = new LinearLayoutManager(context);
        adapter = new RecommendAdapter();
        RecommendModel.get(callback);
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
