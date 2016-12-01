package org.wang.online.ui.main;

import android.databinding.Bindable;
import android.support.v4.view.ViewPager;

import org.wang.online.BR;
import org.wang.online.ui.ViewModel;

/**
 * Created by 王冰 on 2016/11/18.
 */

public class MainViewModel extends ViewModel{
    @Bindable
    private int current;
    @Bindable
    private float recommend = 1;
    @Bindable
    private float column;
    @Bindable
    private float online;
    @Bindable
    private float self;
    private ViewPager.OnPageChangeListener listener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            switch (position) {
                case 0:
                    setRecommend(1 - positionOffset);
                    setColumn(positionOffset);
                    break;
                case 1:
                    setColumn(1 - positionOffset);
                    setOnline(positionOffset);
                    break;
                case 2:
                    setOnline(1 - positionOffset);
                    setSelf(positionOffset);
                    break;
                case 3:
                    setSelf(1 - positionOffset);
                    break;
            }
        }

        @Override
        public void onPageSelected(int position) {
            switch (current) {
                case 0:
                    setRecommend(0);
                    break;
                case 1:
                    setColumn(0);
                    break;
                case 2:
                    setOnline(0);
                    break;
                case 3:
                    setSelf(0);
                    break;
            }
            switch (position) {
                case 0:
                    setRecommend(1);
                    break;
                case 1:
                    setColumn(1);
                    break;
                case 2:
                    setOnline(1);
                    break;
                case 3:
                    setSelf(1);
                    break;
            }

            current = position;
        }
    };
    private MainAdapter adapter;

    public MainViewModel(MainAdapter adapter) {
        this.adapter = adapter;
    }

    public int getCurrent() {
        return current;
    }

    public float getRecommend() {
        return recommend;
    }

    public float getColumn() {
        return column;
    }

    public float getOnline() {
        return online;
    }

    public float getSelf() {
        return self;
    }

    public ViewPager.OnPageChangeListener getListener() {
        return listener;
    }

    public MainAdapter getAdapter() {
        return adapter;
    }

    public void setRecommend(float recommend) {
        this.recommend = recommend;
        notifyPropertyChanged(BR.recommend);
    }

    public void setColumn(float column) {
        this.column = column;
        notifyPropertyChanged(BR.column);
    }

    public void setOnline(float online) {
        this.online = online;
        notifyPropertyChanged(BR.online);
    }

    public void setSelf(float self) {
        this.self = self;
        notifyPropertyChanged(BR.self);
    }

}
