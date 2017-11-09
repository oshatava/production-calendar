package com.osh.android.adapter;

import android.support.v7.widget.RecyclerView;

import com.osh.android.view.BaseDataView;


/**
 * Created by oleg on 12/11/2016.
 */

public class ViewEntityHolder < EntityClass, PresenterClass, ViewClass extends BaseDataView<PresenterClass, EntityClass>>
        extends RecyclerView.ViewHolder {
    ViewClass view;

    public ViewEntityHolder(ViewClass v, final PresenterClass listener) {
        super(v);
        view = v;
        if(listener!=null)
            view.setPresenter(listener);
    }

}
