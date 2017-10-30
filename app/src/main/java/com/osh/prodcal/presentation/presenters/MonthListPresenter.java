package com.osh.prodcal.presentation.presenters;

import com.osh.prodcal.common.presentation.presenter.HasState;
import com.osh.prodcal.common.presentation.presenter.Presenter;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.presentation.views.MonthListView;

/**
 * Created by olegshatava on 27.10.17.
 */

public interface MonthListPresenter extends Presenter<MonthListView>, HasState {

    void onSelectMonth(MonthEntity current);

}
