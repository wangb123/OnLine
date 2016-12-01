package org.wang.online.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.wang.online.R;
import org.wang.online.databinding.FragmentRoomGroupsBinding;
import org.wang.online.ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomGroupsFragment extends BaseFragment<FragmentRoomGroupsBinding> {

    public static RoomGroupsFragment newInstance() {

        Bundle args = new Bundle();

        RoomGroupsFragment fragment = new RoomGroupsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RoomGroupsFragment() {
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_room_groups;
    }


}
