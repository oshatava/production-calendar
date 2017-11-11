package com.osh.prodcal.monthinfo;

import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.dto.Holiday;
import com.osh.prodcal.domain.usecase.GetMonthEntity;
import com.osh.prodcal.domain.usecase.ObserveCurrentMonth;
import com.osh.mvp.presenter.BasePresenterComposite;
import com.osh.prodcal.application.Navigator;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthInfoPresenter extends BasePresenterComposite<MonthInfoContract.View> implements MonthInfoContract.Presenter {

    private MonthKeyEntity key;
    private MonthEntity month;

    private GetMonthEntity getMonthEntity;
    private ObserveCurrentMonth observeCurrentMonth;

    private Navigator navigator;

    @Inject
    public MonthInfoPresenter(Navigator navigator, GetMonthEntity getMonthEntity, ObserveCurrentMonth observeCurrentMonth) {
        super(getMonthEntity, observeCurrentMonth);
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
