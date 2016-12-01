package org.wang.online.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 王冰 on 2016/11/29.
 */

public class Holder<Data, Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {
    Binding binding;

    public Holder(View itemView) {
        super(itemView);
        binding=DataBindingUtil.bind(itemView);
    }

    public Binding getBinding() {
        return binding;
    }

    public void fill(Data data) {
    }
}