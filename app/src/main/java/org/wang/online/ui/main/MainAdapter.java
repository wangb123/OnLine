package org.wang.online.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 王冰 on 2016/11/18.
 */

public class MainAdapter extends FragmentPagerAdapter {
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RecommendFragment.newInstance();
            case 1:
                return RoomGroupsFragment.newInstance();
            case 2:
                return RoomsFragment.newInstance();
            default:
                return SelfFragment.newInstance();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
