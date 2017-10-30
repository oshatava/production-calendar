package com.osh.prodcal.presentation.presenters.impl;

import android.os.Bundle;

import com.osh.prodcal.common.presentation.presenter.BasePresenter;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.usecase.GetMonthEntitiesListForYear;
import com.osh.prodcal.presentation.presenters.YearCalendarPresenter;
import com.osh.prodcal.presentation.views.YearCalendarView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public class YearCalendarPresenterImpl extends BasePresenter<GetMonthEntitiesListForYear, YearCalendarView>
        implements YearCalendarPresenter {

    private Integer key;
    private List<MonthEntity> months = new ArrayList<>();

    public YearCalendarPresenterImpl(GetMonthEntitiesListForYear model, YearCalendarView view) {
        super(model, view);
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

        getModel().execute(key, this::onMonth, this::onError);
    }

    private void onMonth(List<MonthEntity> month) {

        this.months.clear();
        this.months.addAll(month);

        if(hasView()) {
            getView().hideWait();
            getView().showYear(this.months);
        }
    }

}
