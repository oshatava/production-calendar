package com.osh.prodcal.presentation.fragments.year;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osh.android.utils.ViewUtils;
import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.fragments.common.BaseFragment;
import com.osh.prodcal.presentation.presenters.YearCalendarPresenter;
import com.osh.prodcal.presentation.views.YearCalendarView;
import com.osh.prodcal.presentation.views.widgets.MonthCalendarWidget;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class YearCalendarFragment extends BaseFragment<YearCalendarView, YearCalendarPresenter> {

    public static YearCalendarFragment newInstance(int year) {
        YearCalendarFragment f = new YearCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(YearCalendarPresenter.KEY_YEAR, year);
        f.setArguments(args);
        return f;
    }

    public YearCalendarFragment(){}

    @Inject
    public YearCalendarPresenter presenter;

    @Override
    public YearCalendarPresenter getPresenter() {
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
