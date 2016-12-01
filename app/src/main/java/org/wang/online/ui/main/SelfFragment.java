package org.wang.online.ui.main;

import android.os.Bundle;

import org.wang.online.R;
import org.wang.online.databinding.FragmentSelfBinding;
import org.wang.online.ui.BaseFragment;

/**
 * Created by 王冰 on 2016/11/29.
 */

public class SelfFragment extends BaseFragment<FragmentSelfBinding> {
    public static SelfFragment newInstance() {
        Bundle args = new Bundle();
        SelfFragment fragment = new SelfFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_self;
    }
}
