package com.osh.prodcal.presentation.presenters;

import com.osh.mvp.presenter.Presenter;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.views.MonthSelectorView;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthSelectorPresenter extends Presenter<MonthSelectorView> {

    void onCurrentMonthChanged(MonthKeyEntity current);

    void onShowFullYear();

}
