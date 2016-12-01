package org.wang.online.ui.main;


import android.os.Bundle;

import org.wang.online.R;
import org.wang.online.databinding.FragmentRecommendBinding;
import org.wang.online.ui.BaseFragment;

/**
 * 首页推荐
 */
public class RecommendFragment extends BaseFragment<FragmentRecommendBinding> {

    public static RecommendFragment newInstance() {

        Bundle args = new Bundle();

        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RecommendFragment() {
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        getBinding().setData(new RecommendViewModel(getContext()));
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_recommend;
    }

}
