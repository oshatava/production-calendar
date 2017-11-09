package com.osh.prodcal.presentation.views;

import com.osh.mvp.view.View;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.presenters.MonthSelectorPresenter;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthSelectorView extends View<MonthSelectorPresenter> {

    void showMonths(MonthKeyEntity current, List<MonthKeyEntity> months);

    void updateInfo(MonthKeyEntity currentMonth);

}
