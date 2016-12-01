package org.wang.online.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;

import org.wang.online.R;
import org.wang.online.config.ApiService;
import org.wang.online.databinding.FragmentRoomGroupsBinding;
import org.wang.online.databinding.ItemRoomGroupBinding;
import org.wang.online.model.RoomGroup;
import org.wang.online.ui.BaseAdapter;
import org.wang.online.ui.BaseFragment;

import java.util.List;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    BaseAdapter<RoomGroup, ItemRoomGroupBinding> adapter = new BaseAdapter.SimpleAdapter<>(R.layout.item_room_group);

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        getBinding().content.setLayoutManager(new GridLayoutManager(getContext(),3));
        getBinding().content.setAdapter(adapter);

        getGroups();
    }

    private void getGroups() {
        ApiService.Creator.get().allRoomGroups()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<List<RoomGroup>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<List<RoomGroup>> listResponse) {
                        if(listResponse.isSuccessful()){
                            adapter.setList(listResponse.body());
                        }
                    }
                });
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_room_groups;
    }


}
