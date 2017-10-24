package com.osh.zedsampleapp.presentation.presenters;

import com.osh.zedsampleapp.common.presentation.presenter.HasState;
import com.osh.zedsampleapp.common.presentation.presenter.Presenter;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.presentation.views.MonthCalendarView;
import com.osh.zedsampleapp.presentation.views.MonthSelectorView;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthCalendarPresenter extends Presenter<MonthCalendarView>, HasState{
    public static String KEY_MONTH = "MonthCalendarPresenter.Month";

}
