package com.osh.prodcal.presentation.presenters;

import com.osh.mvp.presenter.Presenter;
import com.osh.prodcal.data.dto.Holiday;
import com.osh.prodcal.presentation.views.MonthInfoView;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthInfoPresenter extends Presenter<MonthInfoView> {
    void onHolidayClicked(Holiday holiday);
}
