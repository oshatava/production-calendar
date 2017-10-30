package com.osh.prodcal.common.presentation.adapter;

import android.support.v7.widget.RecyclerView;

import com.osh.prodcal.common.presentation.view.BaseDataListItemView;


/**
 * Created by oleg on 12/11/2016.
 */

public class ViewEntityHolder < EntityClass, ListenerClass, ViewClass extends BaseDataListItemView<EntityClass, ListenerClass>> extends RecyclerView.ViewHolder {
    ViewClass view;

    public ViewEntityHolder(ViewClass v, final ListenerClass listener) {
        super(v);
        view = v;
        if(listener!=null)
            view.setListener(listener);
    }

}
