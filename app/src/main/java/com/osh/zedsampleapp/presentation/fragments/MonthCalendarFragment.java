package com.osh.zedsampleapp.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.osh.zedsampleapp.R;
import com.osh.zedsampleapp.application.di.AppComponent;
import com.osh.zedsampleapp.domain.MonthEntity;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.presentation.presenters.MonthCalendarPresenter;
import com.osh.zedsampleapp.presentation.views.MonthCalendarView;
import com.osh.zedsampleapp.presentation.views.widgets.MonthCalendarWidget;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthCalendarFragment extends BaseFragment<MonthCalendarPresenter> implements MonthCalendarView {


    static MonthCalendarFragment newInstance(MonthKeyEntity keyEntity) {
        MonthCalendarFragment f = new MonthCalendarFragment();

        Bundle args = new Bundle();
        args.putParcelable(MonthCalendarPresenter.KEY_MONTH, keyEntity);
        f.setArguments(args);

        return f;
    }

    private MonthCalendarWidget calendarWidget;

    public MonthCalendarFragment(){
        setRetainInstance(true);
    }

    @Inject
    public MonthCalendarPresenter presenter;

    @Override
    public MonthCalendarPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_month_calendar, container, false);
        initView(fragmentView);
        getPresenter().setView(this);
        return fragmentView;
    }

    private void initView(View view){
        calendarWidget = (MonthCalendarWidget) view.findViewById(R.id.calendarView);
    }

    @Override
    public void showMonth(MonthEntity monthEntity) {
        if(calendarWidget !=null)
            calendarWidget.showData(monthEntity);
    }
}
