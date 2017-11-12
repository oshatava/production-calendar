package com.osh.prodcal.yearlist.monthcalendar;

import android.os.Bundle;

import com.osh.mvp.presenter.BasePresenter;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.usecase.GetMonthEntity;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthCalendarPresenter extends BasePresenter<GetMonthEntity, MonthCalendarContract.View>
        implements MonthCalendarContract.Presenter {

    private MonthKeyEntity key;
    private MonthEntity month;

    @Inject
    public MonthCalendarPresenter(GetMonthEntity model) {
        super(model);
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
