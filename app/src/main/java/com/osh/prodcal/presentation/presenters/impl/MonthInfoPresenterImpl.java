package com.osh.prodcal.presentation.presenters.impl;

import com.osh.prodcal.application.Navigator;
import com.osh.prodcal.common.domain.usecase.UseCaseCollection;
import com.osh.prodcal.common.presentation.presenter.BasePresenterComposite;
import com.osh.prodcal.data.dto.Holiday;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.usecase.GetMonthEntity;
import com.osh.prodcal.domain.usecase.ObserveCurrentMonth;
import com.osh.prodcal.presentation.presenters.MonthInfoPresenter;
import com.osh.prodcal.presentation.views.MonthInfoView;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthInfoPresenterImpl extends BasePresenterComposite<MonthInfoView> implements MonthInfoPresenter {

    private MonthKeyEntity key;
    private MonthEntity month;

    private GetMonthEntity getMonthEntity;
    private ObserveCurrentMonth observeCurrentMonth;

    private Navigator navigator;

    @Inject
    public MonthInfoPresenterImpl(Navigator navigator, GetMonthEntity getMonthEntity, ObserveCurrentMonth observeCurrentMonth, MonthInfoView view) {
        super(view, getMonthEntity, observeCurrentMonth);
        this.getMonthEntity = getMonthEntity;
        this.observeCurrentMonth = observeCurrentMonth;
        this.navigator = navigator;
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
        observeCurrentMonth.execute(null, this::setMonthKey, this::onError);
        reloadInfo();
    }

    private void setMonthKey(MonthKeyEntity monthKey) {
        key = monthKey;
        reloadInfo();
    }

    private void reloadInfo() {
        getMonthEntity.execute(key, this::onMonth, this::onError);
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
