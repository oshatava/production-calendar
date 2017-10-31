package com.osh.prodcal.presentation.presenters.impl;

import android.os.Bundle;

import com.osh.prodcal.application.Navigator;
import com.osh.prodcal.common.presentation.presenter.BasePresenterComposite;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.usecase.GetMonthEntitiesList;
import com.osh.prodcal.domain.usecase.GetMonthEntitiesListForYear;
import com.osh.prodcal.domain.usecase.ObserveCurrentMonth;
import com.osh.prodcal.domain.usecase.SetCurrentMonth;
import com.osh.prodcal.presentation.presenters.MonthListPresenter;
import com.osh.prodcal.presentation.presenters.YearCalendarPresenter;
import com.osh.prodcal.presentation.views.MonthListView;
import com.osh.prodcal.presentation.views.YearCalendarView;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthListPresenterImpl extends BasePresenterComposite<MonthListView>
        implements MonthListPresenter {

    private Integer key;
    private Navigator navigator;
    private GetMonthEntitiesList getMonthEntitiesList;
    private ObserveCurrentMonth observeCurrentMonth;
    private SetCurrentMonth setCurrentMonth;

    public MonthListPresenterImpl(Navigator navigator,
                                  GetMonthEntitiesList getMonthEntitiesList,
                                  ObserveCurrentMonth observeCurrentMonth,
                                  SetCurrentMonth setCurrentMonth) {
        super(null, getMonthEntitiesList, observeCurrentMonth, setCurrentMonth);
        this.navigator = navigator;
        this.getMonthEntitiesList = getMonthEntitiesList;
        this.observeCurrentMonth = observeCurrentMonth;
        this.setCurrentMonth = setCurrentMonth;
    }

    @Override
    public void onStart() {
        super.onStart();
        load();
    }

    private void load() {
        if(hasView())
            getView().showWait();

        observeCurrentMonth.execute(null, this::onCurrentMonth, this::onError);
    }

    private void onCurrentMonth(MonthKeyEntity current) {
        getMonthEntitiesList.execute(null, m->onMonths(current, m), this::onError);
    }


    private void onMonths(MonthKeyEntity current, List<MonthEntity> month) {
        if(hasView()) {
            getView().hideWait();
            getView().showMonths(current, month);
        }
    }

    @Override
    public void onSelectMonth(MonthKeyEntity monthKeyEntity) {
        setCurrentMonth.execute(monthKeyEntity, d->navigator.close(), this::onError);
    }
}
