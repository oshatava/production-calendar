package com.osh.zedsampleapp.presentation.presenters.impl;

import android.os.Bundle;

import com.osh.zedsampleapp.application.Navigator;
import com.osh.zedsampleapp.common.presentation.presenter.BasePresenter;
import com.osh.zedsampleapp.data.dto.Holiday;
import com.osh.zedsampleapp.domain.MonthEntity;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.domain.usecase.GetMonthEntity;
import com.osh.zedsampleapp.presentation.presenters.MonthCalendarPresenter;
import com.osh.zedsampleapp.presentation.presenters.MonthInfoPresenter;
import com.osh.zedsampleapp.presentation.views.MonthCalendarView;
import com.osh.zedsampleapp.presentation.views.MonthInfoView;

import java.util.Calendar;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthInfoPresenterImpl extends BasePresenter<GetMonthEntity, MonthInfoView> implements MonthInfoPresenter {

    private MonthKeyEntity key;
    private MonthEntity month;

    @Override
    public void setMonthKey(MonthKeyEntity monthKey) {
        key = monthKey;
        onStart();
    }
    private Navigator navigator;

    public MonthInfoPresenterImpl(Navigator navigator, GetMonthEntity model, MonthInfoView view) {
        super(model, view);
        this.navigator = navigator;
    }

    @Override
    public void saveState(Bundle bundle) {
        if(bundle!=null)
            bundle.putParcelable(KEY_MONTH, key);
    }

    @Override
    public void restoreState(Bundle bundle) {
        if(bundle!=null) {
            if(bundle.containsKey(KEY_MONTH)) {
                this.key = bundle.getParcelable(KEY_MONTH);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        load();
    }

    private void load() {
        if(hasView())
            getView().showWait();

        if(key==null){
            Calendar calendar = Calendar.getInstance();
            key = new MonthKeyEntity(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        }

        getModel().execute(key, this::onMonth, this::onError);
    }

    private void onMonth(MonthEntity month) {

        this.month = month;

        if(hasView()) {
            getView().hideWait();
            getView().showMonth(this.month);
        }
    }

    @Override
    public void onHolidayClicked(Holiday holiday) {
        navigator.showHolidayInfo(holiday);
    }
}
