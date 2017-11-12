package com.osh.prodcal.yearlist.monthcalendar;

import android.os.Bundle;

import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.BaseFragment;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthCalendarFragment extends BaseFragment<MonthCalendarContract.View, MonthCalendarContract.Presenter> {


    public static MonthCalendarFragment newInstance(MonthKeyEntity keyEntity) {
        MonthCalendarFragment f = new MonthCalendarFragment();

        Bundle args = new Bundle();
        args.putParcelable(MonthCalendarContract.Presenter.KEY_MONTH, keyEntity);
        f.setArguments(args);

        return f;
    }

    public MonthCalendarFragment(){}

    @Inject
    public MonthCalendarPresenter presenter;

    @Override
    public MonthCalendarContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected int getViewResId() {
        return R.layout.fragment_month_calendar;
    }
}
