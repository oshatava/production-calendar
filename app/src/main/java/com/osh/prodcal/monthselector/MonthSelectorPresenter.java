package com.osh.prodcal.monthselector;

import android.util.Log;

import com.osh.mvp.presenter.BasePresenterComposite;
import com.osh.prodcal.application.Navigator;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.usecase.GetMonthsList;
import com.osh.prodcal.domain.usecase.ObserveCurrentMonth;
import com.osh.prodcal.domain.usecase.SetCurrentMonth;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthSelectorPresenter extends BasePresenterComposite<MonthSelectorContract.View>
        implements MonthSelectorContract.Presenter {

    private GetMonthsList getMonthsList;
    private SetCurrentMonth setCurrentMonth;
    private ObserveCurrentMonth observeCurrentMonth;
    private Navigator navigator;

    @Inject
    public MonthSelectorPresenter(Navigator navigator,
                                  GetMonthsList getMonthsList,
                                  ObserveCurrentMonth observeCurrentMonth,
                                  SetCurrentMonth setCurrentMonth) {
        super(getMonthsList, observeCurrentMonth, setCurrentMonth);
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
            Log.d(TAG, "IView is not atached");
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
