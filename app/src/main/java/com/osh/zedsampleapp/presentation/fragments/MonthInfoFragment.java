package com.osh.zedsampleapp.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osh.zedsampleapp.R;
import com.osh.zedsampleapp.application.di.AppComponent;
import com.osh.zedsampleapp.data.dto.Holiday;
import com.osh.zedsampleapp.domain.MonthEntity;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.presentation.presenters.MonthCalendarPresenter;
import com.osh.zedsampleapp.presentation.presenters.MonthInfoPresenter;
import com.osh.zedsampleapp.presentation.views.HolidayItemView;
import com.osh.zedsampleapp.presentation.views.MonthCalendarView;
import com.osh.zedsampleapp.presentation.views.MonthInfoView;
import com.osh.zedsampleapp.presentation.views.utils.ViewUtils;
import com.osh.zedsampleapp.presentation.views.widgets.MonthCalendarWidget;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthInfoFragment extends BaseFragment<MonthInfoPresenter> implements MonthInfoView {

    static MonthInfoFragment newInstance(MonthKeyEntity keyEntity) {
        MonthInfoFragment f = new MonthInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(MonthInfoPresenter.KEY_MONTH, keyEntity);
        f.setArguments(args);
        return f;
    }

    private ViewGroup holidaysContainer;

    public void setMonthKey(MonthKeyEntity current) {
        getPresenter().setMonthKey(current);
    }

    public MonthInfoFragment(){
        setRetainInstance(true);
    }

    @Inject
    public MonthInfoPresenter presenter;

    @Override
    public MonthInfoPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_month_info, container, false);
        initView(fragmentView);
        getPresenter().setView(this);
        return fragmentView;
    }

    private void initView(View view){
        holidaysContainer = ViewUtils.findViewById(view, R.id.holidaysContainer);
    }

    @Override
    public void showMonth(MonthEntity monthEntity) {
        if(monthEntity.getHolidays().size()>0){
            ViewUtils.show(getView(), R.id.holidaysContainer);
            ViewUtils.show(getView(), R.id.holidaysTitle);
            if(holidaysContainer!=null) {
                holidaysContainer.removeAllViews();
                for (Holiday holiday : monthEntity.getHolidays()) {
                    HolidayItemView item = ViewUtils.inflate(holidaysContainer, R.layout.view_holiday_info_item);
                    item.showData(holiday);
                    item.setOnClickListener((v) -> presenter.onHolidayClicked(holiday));
                    holidaysContainer.addView(item);
                }
            }
        }else{
            ViewUtils.hide(getView(), R.id.holidaysContainer);
            ViewUtils.hide(getView(), R.id.holidaysTitle);
        }

        ViewUtils.text(getView(), R.id.numberOfDay, Integer.toString(monthEntity.getNumberOfDay()));
        ViewUtils.text(getView(), R.id.numberOfNonWorkingDay, Integer.toString(monthEntity.getNumberOfNonWorkingDay()));
        ViewUtils.text(getView(), R.id.numberOfWorkingDay, Integer.toString(monthEntity.getNumberOfWorkingDay()));

        ViewUtils.text(getView(), R.id.numberOfWorkingHours40, String.format("%.1f",monthEntity.getNumberOfHoursForWorkWeek(40)));
        ViewUtils.text(getView(), R.id.numberOfWorkingHours36, String.format("%.1f",monthEntity.getNumberOfHoursForWorkWeek(36)));
        ViewUtils.text(getView(), R.id.numberOfWorkingHours24, String.format("%.1f",monthEntity.getNumberOfHoursForWorkWeek(24)));

        ViewUtils.text(getView(), R.id.quarter, Integer.toString(monthEntity.getCurrentQuarter()));
        ViewUtils.text(getView(), R.id.halfYear, Integer.toString(monthEntity.getCurrentHalfYear()));
        ViewUtils.text(getView(), R.id.year, Integer.toString(monthEntity.getCurrentYear()));

    }

}
