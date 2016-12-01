package org.wang.online.ui.main;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import org.wang.online.R;
import org.wang.online.databinding.ImgBannerBinding;
import org.wang.online.model.Room;
import org.wang.online.ui.ViewModel;

import java.util.List;

/**
 * Created by 王冰 on 2016/11/30.
 */

public class RecommendBannerViewModel extends ViewModel {

    @Bindable
    String title;
    Adapter adapter = new Adapter();
    ViewPager.OnPageChangeListener listener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            setTitle(adapter.getPageTitle(position).toString());
        }
    };

    public RecommendBannerViewModel() {
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public ViewPager.OnPageChangeListener getListener() {
        return listener;
    }

    public void setRooms(List<Room> rooms) {
        if (TextUtils.isEmpty(title) && rooms.size() > 0) setTitle(rooms.get(0).getPlayer().getTitle());
        adapter.setData(rooms);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }


    public class Adapter extends PagerAdapter {
        List<Room> rooms;

        public Adapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImgBannerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.img_banner, container, false);
            binding.setRoom(rooms.get(position));
            container.addView(binding.getRoot());
            return binding.getRoot();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return rooms == null ? 0 : rooms.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return rooms.get(position % getCount()).getPlayer().getTitle();
        }

        public void setData(List<Room> data) {
            if(this.rooms == data)return;
            this.rooms = data;
            notifyDataSetChanged();
        }

        @Override
        public String toString() {
            return super.toString() + " " + getCount();
        }
    }
}
