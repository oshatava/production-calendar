package com.osh.prodcal.presentation.presenters;

import com.osh.mvp.presenter.HasState;
import com.osh.mvp.presenter.Presenter;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.views.YearCalendarView;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface YearCalendarPresenter extends Presenter<YearCalendarView>, HasState {
    String KEY_YEAR = "YearCalendarPresenter.YEAR";

    void onSelectMonth(MonthKeyEntity monthKeyEntity);
}
