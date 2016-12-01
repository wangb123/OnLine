package org.wang.online.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import org.wang.online.R;
import org.wang.online.config.Callback;
import org.wang.online.config.Error;
import org.wang.online.databinding.FragmentRoomsBinding;
import org.wang.online.databinding.ItemRoomBinding;
import org.wang.online.model.PageModel;
import org.wang.online.model.Player;
import org.wang.online.ui.BaseAdapter;
import org.wang.online.ui.BaseFragment;
import org.wang.online.view.PagingRecyclerView;

import java.util.List;

/**
 * Created by 王冰 on 2016/11/29.
 */

public class RoomsFragment extends BaseFragment<FragmentRoomsBinding> implements PagingRecyclerView.OnLoadMoreListener {
    public static RoomsFragment newInstance() {

        Bundle args = new Bundle();

        RoomsFragment fragment = new RoomsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    BaseAdapter<Player, ItemRoomBinding> adapter = new BaseAdapter.SimpleAdapter<>(R.layout.item_room);
    SwipeRefreshLayout.OnRefreshListener listener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            getBinding().content.setState(PagingRecyclerView.State.Refresh);
        }
    };

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        getBinding().refresh.setOnRefreshListener(listener);
        getBinding().content.setOnLoadMoreListener(this);
        getBinding().content.setLayoutManager(new GridLayoutManager(getContext(), 2));
        getBinding().content.setAdapter(adapter);
//        onLoadMore(1);
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_rooms;
    }

    @Override
    public void onLoadMore(final int page) {
        PageModel.getPlayers(page, new Callback.SimpleCallback<List<Player>>() {
            @Override
            public void onSuccess(List<Player> players) {
                if (page == 1)
                    adapter.removeAll();
                adapter.addItems(players);
            }

            @Override
            public void onFail(Error error) {

            }

            @Override
            public void onAfter() {
                if (getBinding().refresh.isRefreshing()) getBinding().refresh.setRefreshing(false);
            }
        });
    }
}
