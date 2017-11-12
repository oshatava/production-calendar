package com.osh.prodcal.monthinfo.monthselector;

import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.BaseFragment;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthSelectorFragment extends BaseFragment<MonthSelectorContract.View, MonthSelectorContract.Presenter> {

    public MonthSelectorFragment(){

    }

    @Inject
    public MonthSelectorPresenter presenter;

    @Override
    public MonthSelectorContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected int getViewResId() {
        return R.layout.fragment_month_selector;
    }

}
