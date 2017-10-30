package com.osh.prodcal.presentation.presenters;

import com.osh.prodcal.common.presentation.presenter.HasState;
import com.osh.prodcal.common.presentation.presenter.Presenter;
import com.osh.prodcal.presentation.views.YearCalendarView;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface YearCalendarPresenter extends Presenter<YearCalendarView>, HasState{
    public static String KEY_YEAR = "YearCalendarPresenter.YEAR";

}
