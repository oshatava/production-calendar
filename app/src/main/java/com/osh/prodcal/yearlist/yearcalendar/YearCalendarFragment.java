package com.osh.prodcal.yearlist.yearcalendar;

import android.os.Bundle;

import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.BaseFragment;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class YearCalendarFragment extends BaseFragment<YearCalendarContract.View, YearCalendarContract.Presenter> {

    public static YearCalendarFragment newInstance(int year) {
        YearCalendarFragment f = new YearCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(YearCalendarContract.Presenter.KEY_YEAR, year);
        f.setArguments(args);
        return f;
    }

    public YearCalendarFragment(){}

    @Inject
    public YearCalendarPresenter presenter;

    @Override
    public YearCalendarContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected int getViewResId() {
        return R.layout.fragment_year_calendar;
    }

}
