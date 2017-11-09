package com.osh.prodcal.presentation.presenters;

import com.osh.mvp.presenter.Presenter;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.views.MonthListView;

/**
 * Created by olegshatava on 27.10.17.
 */

public interface MonthListPresenter extends Presenter<MonthListView> {

    void onSelectMonth(MonthKeyEntity current);

    void onCancel();
}
