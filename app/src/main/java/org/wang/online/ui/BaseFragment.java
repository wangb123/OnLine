package org.wang.online.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王冰 on 2016/11/18.
 */

public abstract class BaseFragment<Bind extends ViewDataBinding> extends Fragment {


    private Bind binding;//ViewDataBinding

    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            getParams(getArguments());
    }

    protected void getParams(Bundle args) {
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layoutId() == 0) return super.onCreateView(inflater, container, savedInstanceState);
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
            onCreateView(savedInstanceState);
        }
        return binding.getRoot();
    }

    protected abstract void onCreateView(Bundle savedInstanceState);

    protected abstract @LayoutRes int layoutId();

    public Bind getBinding() {
        return binding;
    }
}
