package com.osh.prodcal.presentation.presenters.impl;

import android.os.Bundle;

import com.osh.mvp.presenter.BasePresenterComposite;
import com.osh.prodcal.application.Navigator;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.usecase.GetMonthEntitiesListForYear;
import com.osh.prodcal.domain.usecase.ObserveCurrentMonth;
import com.osh.prodcal.domain.usecase.SetCurrentMonth;
import com.osh.prodcal.presentation.presenters.YearCalendarPresenter;
import com.osh.prodcal.presentation.views.YearCalendarView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class YearCalendarPresenterImpl extends BasePresenterComposite<YearCalendarView>
        implements YearCalendarPresenter {

    private Integer key;
    private Navigator navigator;
    private GetMonthEntitiesListForYear getMonthEntitiesListForYear;
    private SetCurrentMonth setCurrentMonth;

    @Inject
    public YearCalendarPresenterImpl(Navigator navigator,
                                     GetMonthEntitiesListForYear getMonthEntitiesListForYear,
                                     SetCurrentMonth setCurrentMonth) {
        super(getMonthEntitiesListForYear, setCurrentMonth);
        this.navigator = navigator;
        this.getMonthEntitiesListForYear = getMonthEntitiesListForYear;
        this.setCurrentMonth = setCurrentMonth;
    }

    @Override
    public void saveState(Bundle bundle) {
        if(bundle!=null)
            bundle.putInt(KEY_YEAR, key);
    }

    @Override
    public void restoreState(Bundle bundle) {
        if(bundle!=null) {
            if(bundle.containsKey(KEY_YEAR)) {
                this.key = bundle.getInt(KEY_YEAR);
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
        getMonthEntitiesListForYear.execute(key, this::onMonth, this::onError);
    }

    private void onMonth(List<MonthEntity> month) {
        if(hasView()) {
            getView().hideWait();
            getView().showYear(month);
        }
    }

    @Override
    public void onSelectMonth(MonthKeyEntity monthKeyEntity) {
        setCurrentMonth.execute(monthKeyEntity, d->navigator.close(), this::onError);
    }
}
