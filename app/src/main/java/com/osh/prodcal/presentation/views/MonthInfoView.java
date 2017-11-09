package com.osh.prodcal.presentation.views;

import com.osh.mvp.view.View;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.presentation.presenters.MonthInfoPresenter;

/**
 * Created by olegshatava on 24.10.17.
 */

public interface MonthInfoView extends View<MonthInfoPresenter> {
    void showMonth(MonthEntity monthEntity);
}

