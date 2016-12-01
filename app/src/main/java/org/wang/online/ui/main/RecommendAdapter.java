package org.wang.online.ui.main;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.wang.online.R;
import org.wang.online.databinding.HomeBannerBinding;
import org.wang.online.databinding.HotGroupBinding;
import org.wang.online.databinding.ItemHotGroupBinding;
import org.wang.online.databinding.ItemRecommendBinding;
import org.wang.online.databinding.ItemRoomBinding;
import org.wang.online.model.Room;
import org.wang.online.model.RoomGroup;
import org.wang.online.ui.BaseAdapter;
import org.wang.online.ui.Holder;
import org.wang.online.util.Utils;

import java.util.List;

/**
 * Created by 王冰 on 2016/11/29.
 */

public class RecommendAdapter extends RecyclerView.Adapter<Holder> {
    RecommendModel model;

    public void setModel(RecommendModel model) {
        this.model = model;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new BannerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_banner, parent, false));
            case 1:
                return new HotGroupHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_group, parent, false));
//            case 2:
//                return new RecommendHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, null, false));
            default:
                return new RecommendHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        switch (getItemViewType(position)) {
            case 0:
                holder.fill(model.getIndex());
                break;
            case 1:
                holder.fill(model.getHotGroup());
                break;
//            case 2:
//                holder.fill(model.getList().get(position));
//                break;
            default:
                holder.fill(model.getList().get(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TextUtils.isEmpty(model.getList().get(position).getCategory_slug()) ? position : -1;
//        return position ;
    }

    @Override
    public int getItemCount() {
//        return model == null || model.getList() == null ? 0 : 3;
        return model == null || model.getList() == null ? 0 : model.getList().size();
    }

    private class BannerHolder extends Holder<List<Room>, HomeBannerBinding> {

        public BannerHolder(View itemView) {
            super(itemView);
            itemView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Utils.getScreenWidth() / 2.2)));
            getBinding().setModel(new RecommendBannerViewModel());
        }

        @Override
        public void fill(List<Room> rooms) {
            getBinding().getModel().setRooms(rooms);
        }
    }

    private class HotGroupHolder extends Holder<List<RoomGroup>, HotGroupBinding> {
        BaseAdapter<RoomGroup, ItemHotGroupBinding> adapter = new BaseAdapter.SimpleAdapter<>(R.layout.item_hot_group);

        public HotGroupHolder(View itemView) {
            super(itemView);
            getBinding().content.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            getBinding().content.setAdapter(adapter);
        }

        @Override
        public void fill(List<RoomGroup> roomGroups) {
            adapter.setList(roomGroups);
        }
    }

    private class RecommendHolder extends Holder<RoomGroup, ItemRecommendBinding> implements View.OnClickListener {
        int page;
        BaseAdapter<Room, ItemRoomBinding> adapter = new BaseAdapter.SimpleAdapter<Room, ItemRoomBinding>(R.layout.item_room){
            @Override
            public void onBindViewHolder(Holder<Room, ItemRoomBinding> holder, int position) {
             holder.getBinding().setModel(getList().get(position).getPlayer());
            }
        };

        public RecommendHolder(View itemView) {
            super(itemView);
            getBinding().content.setLayoutManager(new GridLayoutManager(itemView.getContext(), 2));
            getBinding().content.setAdapter(adapter);
            getBinding().action.setOnClickListener(this);
        }

        @Override
        public void fill(RoomGroup roomGroup) {
            getBinding().setModel(roomGroup);
            if (TextUtils.isEmpty(roomGroup.getCategory_slug())) {
                if (model.getRecommend().size() <= 2) {
                    adapter.setList(model.getRecommend());
                    return;
                }
                if (2 * page + 2 > model.getRecommend().size()) {
                    page = 0;
                }
                adapter.setList(model.getRecommend().subList(2 * page, 2 * page + 2));
                return;
            }
            switch (roomGroup.getCategory_slug()) {
                case "lol":
                    adapter.setList(model.getLol());
                    break;
                case "beauty":
                    adapter.setList(model.getBeauty());
                    break;
                case "heartstone":
                    adapter.setList(model.getHeartstone());
                    break;
                case "huwai":
                    adapter.setList(model.getHuwai());
                    break;
                case "overwatch":
                    adapter.setList(model.getOverwatch());
                    break;
                case "blizzard":
                    adapter.setList(model.getBlizzard());
                    break;
                case "qqfeiche":
                    adapter.setList(model.getQqfeiche());
                    break;
                case "mobilegame":
                    adapter.setList(model.getMobilegame());
                    break;
                case "wangzhe":
                    adapter.setList(model.getWangzhe());
                    break;
                case "dota2":
                    adapter.setList(model.getDota2());
                    break;
                case "tvgame":
                    adapter.setList(model.getTvgame());
                    break;
                case "webgame":
                    adapter.setList(model.getWebgame());
                    break;
                case "dnf":
                    adapter.setList(model.getDnf());
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(getBinding().getModel().getCategory_slug())) {
                if (page == Integer.MAX_VALUE) page = 0;
                page++;
                fill(getBinding().getModel());
            } else {
                getBinding().getModel().onClick(v);
            }
        }
    }

}
