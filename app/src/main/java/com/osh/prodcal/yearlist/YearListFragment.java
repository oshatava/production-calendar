package com.osh.prodcal.yearlist;

import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.presentation.BaseFragment;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class YearListFragment extends BaseFragment<YearListContract.View, YearListContract.Presenter>  {


    public YearListFragment(){
    }

    @Inject
    public YearListPresenter presenter;

    @Override
    public YearListContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected int getViewResId() {
        return R.layout.fragment_month_selector_full_year;
    }
}
