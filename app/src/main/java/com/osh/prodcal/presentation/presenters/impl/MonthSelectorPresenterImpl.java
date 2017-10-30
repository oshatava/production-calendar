package com.osh.prodcal.presentation.presenters.impl;

import android.util.Log;

import com.osh.prodcal.application.Navigator;
import com.osh.prodcal.common.domain.usecase.UseCaseCollection;
import com.osh.prodcal.common.presentation.presenter.BasePresenterComposite;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.usecase.GetMonthsList;
import com.osh.prodcal.domain.usecase.ObserveCurrentMonth;
import com.osh.prodcal.domain.usecase.SetCurrentMonth;
import com.osh.prodcal.presentation.presenters.MonthSelectorPresenter;
import com.osh.prodcal.presentation.views.MonthSelectorView;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthSelectorPresenterImpl extends BasePresenterComposite<MonthSelectorView>
        implements MonthSelectorPresenter {

    private GetMonthsList getMonthsList;
    private SetCurrentMonth setCurrentMonth;
    private ObserveCurrentMonth observeCurrentMonth;
    private Navigator navigator;

    public MonthSelectorPresenterImpl(Navigator navigator,
                                      GetMonthsList getMonthsList,
                                      ObserveCurrentMonth observeCurrentMonth,
                                      SetCurrentMonth setCurrentMonth,
                                      MonthSelectorView view) {
        super(UseCaseCollection.builder().add(getMonthsList).add(setCurrentMonth).add(observeCurrentMonth).build(), view);
        this.navigator = navigator;
        this.getMonthsList = getMonthsList;
        this.setCurrentMonth = setCurrentMonth;
        this.observeCurrentMonth = observeCurrentMonth;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (hasView())
            getView().showWait();
        observeCurrentMonth.execute(null, this::onCurrentMonthGot, this::onError);
    }

    private void onCurrentMonthGot(MonthKeyEntity currentMonth) {
        getMonthsList.execute(null, monthList -> onMonths(currentMonth, monthList), this::onError);
    }

    private void onMonths(MonthKeyEntity currentMonth, List<MonthKeyEntity> monthList) {
        if (hasView()) {
            getView().hideWait();
            getView().showMonths(currentMonth, monthList);
        }else{
            Log.d(TAG, "View is not atached");
        }
    }

    @Override
    public void onCurrentMonthChanged(MonthKeyEntity current) {
        setCurrentMonth.execute(current, this::updateInfo, this::onError);
    }

    private void updateInfo(MonthKeyEntity monthKeyEntity) {
        if (hasView()) {
            getView().updateInfo(monthKeyEntity);
        }
    }

    @Override
    public void onShowFullYear() {
        navigator.showMonthSelectorActivity();
    }

}
