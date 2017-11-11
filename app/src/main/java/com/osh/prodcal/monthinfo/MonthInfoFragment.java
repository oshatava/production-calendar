package com.osh.prodcal.monthinfo;

import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.presentation.BaseFragment;

import javax.inject.Inject;

public class MonthInfoFragment extends BaseFragment<MonthInfoContract.View, MonthInfoContract.Presenter> {


    public MonthInfoFragment() {}

    @Inject
    public MonthInfoPresenter presenter;

    @Override
    public MonthInfoContract.Presenter getPresenter() {
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
