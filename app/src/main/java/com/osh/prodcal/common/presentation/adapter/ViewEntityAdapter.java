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
        extends RecyclerView.Adapter<ViewEntityHolder<EntityClass, ListenerClass, ViewClass>> {

    private LayoutResFabric layoutResFabric;
    private final List<EntityClass> items = new ArrayList<>();
    private ListenerClass itemListener;
    private ItemViewType<EntityClass> itemViewType;

    public ViewEntityAdapter(int itemLayoutResId, ListenerClass itemListener) {
        this.layoutResFabric = data -> itemLayoutResId;
        this.itemListener = itemListener;
    }


    public ViewEntityAdapter(ItemViewType<EntityClass> itemViewType, ListenerClass itemListener) {
        this.layoutResFabric = data->data;
        this.itemListener = itemListener;
        this.itemViewType = itemViewType;

    }


    public void setItems(List<EntityClass> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (itemViewType != null) {
            return itemViewType.getLayoutRes(getItem(position));
        } else
            return super.getItemViewType(position);

    }

    @Override
    public ViewEntityHolder<EntityClass, ListenerClass, ViewClass> onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewClass v = (ViewClass) LayoutInflater.from(parent.getContext())
                .inflate(layoutResFabric.getLayoutRes(viewType), parent, false);

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

    public interface LayoutResFabric {
        int getLayoutRes(int viewType);
    }

    public interface ItemViewType<EntityClass> {
        int getLayoutRes(EntityClass entityClass);
    }
}
