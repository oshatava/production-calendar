package com.osh.zedsampleapp.presentation.presenters.impl;

import android.os.Bundle;

import com.osh.zedsampleapp.common.presentation.presenter.BasePresenter;
import com.osh.zedsampleapp.domain.MonthEntity;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.domain.usecase.GetMonthEntity;
import com.osh.zedsampleapp.domain.usecase.GetMonthsList;
import com.osh.zedsampleapp.presentation.presenters.MonthCalendarPresenter;
import com.osh.zedsampleapp.presentation.presenters.MonthSelectorPresenter;
import com.osh.zedsampleapp.presentation.views.MonthCalendarView;
import com.osh.zedsampleapp.presentation.views.MonthSelectorView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthCalendarPresenterImpl extends BasePresenter<GetMonthEntity, MonthCalendarView> implements MonthCalendarPresenter {

    private MonthKeyEntity key;
    private MonthEntity month;

    public MonthCalendarPresenterImpl(GetMonthEntity model, MonthCalendarView view) {
        super(model, view);
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

        getModel().execute(key, this::onMonth, this::onError);
    }

    private void onMonth(MonthEntity month) {

        this.month = month;

        if(hasView()) {
            getView().hideWait();
            getView().showMonth(this.month);
        }
    }

}
