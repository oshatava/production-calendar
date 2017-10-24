package com.osh.zedsampleapp.presentation.presenters.impl;

import android.os.Bundle;

import com.osh.zedsampleapp.application.Navigator;
import com.osh.zedsampleapp.common.presentation.presenter.BasePresenter;
import com.osh.zedsampleapp.data.dto.MonthKey;
import com.osh.zedsampleapp.domain.MonthEntity;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.domain.usecase.GetMonthEntity;
import com.osh.zedsampleapp.domain.usecase.GetMonthsList;
import com.osh.zedsampleapp.presentation.presenters.MonthSelectorPresenter;
import com.osh.zedsampleapp.presentation.views.MonthSelectorView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthSelectorPresenterImpl extends BasePresenter<GetMonthsList, MonthSelectorView> implements MonthSelectorPresenter {

    private static String KEY_CURRENT_MONTH = "MonthSelectorPresenter.CurrentMonth";

    private MonthKeyEntity currentMonth;
    private final List<MonthKeyEntity> monthList = new ArrayList<>();

    private Calendar calendar = Calendar.getInstance();

    public MonthSelectorPresenterImpl( GetMonthsList model, MonthSelectorView view) {
        super(model, view);
    }

    private void setUpCurrentMonth(){
        if(monthList.size()==0) return;

        if(currentMonth==null){
            // Select current month by default
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            MonthKeyEntity tempCurrentMonth = new MonthKeyEntity(year, month);
            if(monthList.contains(tempCurrentMonth)) {
                currentMonth = tempCurrentMonth;
            }else{
                // Current year out of possible values. Select first.
                currentMonth = monthList.get(0);
            }
        }

    }

    @Override
    public void saveState(Bundle bundle) {
        if(bundle!=null)
            bundle.putParcelable(KEY_CURRENT_MONTH, currentMonth);
    }

    @Override
    public void restoreState(Bundle bundle) {
        if(bundle!=null) {
            if(bundle.containsKey(KEY_CURRENT_MONTH)) {
                this.currentMonth = bundle.getParcelable(KEY_CURRENT_MONTH);
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

        getModel().execute(null, this::onMonths, this::onError);
    }

    private void onMonths(List<MonthKeyEntity> monthList) {
        this.monthList.clear();
        this.monthList.addAll(monthList);
        setUpCurrentMonth();

        if(hasView()) {
            getView().hideWait();
            getView().showMonths(currentMonth, monthList);
        }

    }

    @Override
    public void onCurrentMonthChanged(MonthKeyEntity current) {
        currentMonth = current;
        getView().updateInfo(currentMonth);
    }


}
