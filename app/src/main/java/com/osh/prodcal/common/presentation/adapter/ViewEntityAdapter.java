package com.osh.prodcal.common.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.osh.prodcal.common.presentation.view.BaseDataListItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 12/11/2016.
 */

public class ViewEntityAdapter <ViewClass extends BaseDataListItemView<EntityClass, ListenerClass>, EntityClass, ListenerClass>
        extends RecyclerView.Adapter<ViewEntityHolder<EntityClass, ListenerClass, ViewClass>>{

    private int itemLayoutResId;
    private final List<EntityClass> items = new ArrayList<>();
    private ListenerClass itemListener;

    public ViewEntityAdapter(int itemLayoutResId, ListenerClass itemListener) {
        this.itemLayoutResId = itemLayoutResId;
        this.itemListener = itemListener;
    }

    public void setItems(List<EntityClass> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public ViewEntityHolder<EntityClass, ListenerClass, ViewClass> onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewClass v = (ViewClass) LayoutInflater.from(parent.getContext())
                .inflate(itemLayoutResId, parent, false);

        ViewEntityHolder<EntityClass, ListenerClass, ViewClass> vh = new ViewEntityHolder<>(v, itemListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewEntityHolder<EntityClass, ListenerClass, ViewClass> holder, int position) {
        holder.view.showData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public EntityClass getItem(int position) {
        return items.get(position);
    }
}
