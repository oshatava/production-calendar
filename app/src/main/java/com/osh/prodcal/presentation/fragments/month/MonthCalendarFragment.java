package com.osh.prodcal.presentation.fragments.month;

import android.os.Bundle;

import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.fragments.common.BaseFragment;
import com.osh.prodcal.presentation.presenters.MonthCalendarPresenter;
import com.osh.prodcal.presentation.presenters.impl.MonthCalendarPresenterImpl;
import com.osh.prodcal.presentation.views.MonthCalendarView;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthCalendarFragment extends BaseFragment<MonthCalendarView, MonthCalendarPresenter> {


    public static MonthCalendarFragment newInstance(MonthKeyEntity keyEntity) {
        MonthCalendarFragment f = new MonthCalendarFragment();

        Bundle args = new Bundle();
        args.putParcelable(MonthCalendarPresenter.KEY_MONTH, keyEntity);
        f.setArguments(args);

        return f;
    }

    public MonthCalendarFragment(){}

    @Inject
    public MonthCalendarPresenterImpl presenter;

    @Override
    public MonthCalendarPresenter getPresenter() {
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
