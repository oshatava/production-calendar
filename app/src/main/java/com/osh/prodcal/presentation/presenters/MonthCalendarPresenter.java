package com.osh.prodcal.presentation.presenters;

import com.osh.mvp.presenter.HasState;
import com.osh.mvp.presenter.Presenter;
import com.osh.prodcal.presentation.views.MonthCalendarView;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthCalendarPresenter extends Presenter<MonthCalendarView>, HasState {
    public static String KEY_MONTH = "MonthCalendarPresenter.Month";

}
