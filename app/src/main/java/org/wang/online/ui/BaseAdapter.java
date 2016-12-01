package org.wang.online.ui;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的RecyclerView.Adapter
 * Created by 王冰 on 2016/11/29.
 */

public abstract class BaseAdapter<Data, Binding extends ViewDataBinding> extends RecyclerView.Adapter<Holder<Data, Binding>> {
    private List<Data> list;

    public BaseAdapter() {
    }

    public BaseAdapter(List<Data> list) {
        this.list = list;
    }

    @Override
    public final Holder<Data, Binding> onCreateViewHolder(ViewGroup parent, int viewType) {
        Holder<Data, Binding> holder = new Holder<>(LayoutInflater.from(parent.getContext()).inflate(holderLayout(), null, false));
        initHolder(holder, viewType);
        return holder;
    }

    protected void initHolder(Holder<Data, Binding> holder, int viewType) {

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public List<Data> getList() {
        return list;
    }

    public void setList(List<Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(Data data) {
        if (this.list == null) this.list = new ArrayList<>();
        this.list.add(data);
        notifyItemInserted(this.list.size() - 1);
    }

    public void addItem(Data data, int position) {
        if (this.list == null) this.list = new ArrayList<>();
        this.list.add(position, data);
        notifyItemInserted(position);
    }

    public void addItems(List<Data> datas) {
        if (this.list == null) this.list = new ArrayList<>();
        this.list.addAll(datas);
        notifyItemRangeChanged(this.list.size() - datas.size(), datas.size());
    }

    public void addItems(List<Data> datas, int position) {
        if (this.list == null) this.list = new ArrayList<>();
        this.list.addAll(position, datas);
        notifyItemRangeChanged(position, datas.size());
    }

    public void remove(int position) {
        if (getItemCount() == 0 || position < 0 || position > getItemCount() - 1) return;
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(Data data) {
        if (getItemCount() == 0) return;
        remove(this.list.indexOf(data));
    }

    public abstract int holderLayout();

    public static class SimpleAdapter<Data, Binding extends ViewDataBinding> extends BaseAdapter<Data, Binding> {
        int holderLayout;

        public SimpleAdapter(int holderLayout) {
            this.holderLayout = holderLayout;
        }

        @Override
        public int holderLayout() {
            return holderLayout;
        }

        @Override
        public void onBindViewHolder(Holder<Data, Binding> holder, int position) {
            holder.getBinding().setVariable(BR.model, getList().get(position));
        }
    }
}
