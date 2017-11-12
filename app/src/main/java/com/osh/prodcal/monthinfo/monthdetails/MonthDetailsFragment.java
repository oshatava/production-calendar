package com.osh.prodcal.monthinfo.monthdetails;

import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.BaseFragment;

import javax.inject.Inject;

public class MonthDetailsFragment extends BaseFragment<MonthDetailsContract.View, MonthDetailsContract.Presenter> {


    public MonthDetailsFragment() {}

    @Inject
    public MonthDetailsPresenter presenter;

    @Override
    public MonthDetailsContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected int getViewResId() {
        return R.layout.fragment_month_info;
    }
}
