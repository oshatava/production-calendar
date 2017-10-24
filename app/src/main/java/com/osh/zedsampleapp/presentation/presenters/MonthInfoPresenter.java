package com.osh.zedsampleapp.presentation.presenters;

import com.osh.zedsampleapp.common.presentation.presenter.HasState;
import com.osh.zedsampleapp.common.presentation.presenter.Presenter;
import com.osh.zedsampleapp.data.dto.Holiday;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.presentation.views.MonthCalendarView;
import com.osh.zedsampleapp.presentation.views.MonthInfoView;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthInfoPresenter extends Presenter<MonthInfoView>, HasState{
    public static String KEY_MONTH = "MonthInfoPresenter.Month";

    void setMonthKey(MonthKeyEntity monthKey);

    void onHolidayClicked(Holiday holiday);
}
