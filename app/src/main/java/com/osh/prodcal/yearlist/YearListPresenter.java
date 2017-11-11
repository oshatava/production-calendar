package com.osh.prodcal.yearlist;

import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.usecase.GetMonthEntitiesList;
import com.osh.prodcal.domain.usecase.ObserveCurrentMonth;
import com.osh.prodcal.domain.usecase.SetCurrentMonth;
import com.osh.mvp.presenter.BasePresenterComposite;
import com.osh.prodcal.application.Navigator;
import com.osh.prodcal.yearlist.YearListContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class YearListPresenter extends BasePresenterComposite<YearListContract.View>
        implements YearListContract.Presenter {

    private Integer key;
    private Navigator navigator;
    private GetMonthEntitiesList getMonthEntitiesList;
    private ObserveCurrentMonth observeCurrentMonth;
    private SetCurrentMonth setCurrentMonth;

    @Inject
    public YearListPresenter(Navigator navigator,
                             GetMonthEntitiesList getMonthEntitiesList,
                             ObserveCurrentMonth observeCurrentMonth,
                             SetCurrentMonth setCurrentMonth) {
        super(getMonthEntitiesList, observeCurrentMonth, setCurrentMonth);
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

    @Override
    public void onCancel() {
        navigator.close();
    }
}
