package com.osh.zedsampleapp.presentation.presenters;

import com.osh.zedsampleapp.common.presentation.presenter.HasState;
import com.osh.zedsampleapp.common.presentation.presenter.Presenter;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.presentation.views.MonthSelectorView;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthSelectorPresenter extends Presenter<MonthSelectorView>, HasState {

    void onCurrentMonthChanged(MonthKeyEntity current);

}
