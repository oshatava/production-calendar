package com.osh.prodcal.presentation.views;

import com.osh.mvp.view.View;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.presenters.MonthListPresenter;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthListView extends View<MonthListPresenter> {
    void showMonths(MonthKeyEntity current, List<MonthEntity> months);
}
