package com.osh.prodcal.yearlist.monthcalendar;

import com.osh.prodcal.domain.MonthEntity;
import com.osh.mvp.presenter.HasState;
import com.osh.mvp.presenter.IPresenter;
import com.osh.mvp.view.IView;

/**
 * Created by olegshatava on 10.11.17.
 */

public interface MonthCalendarContract {

    interface Presenter extends IPresenter<View>, HasState {
        String KEY_MONTH = "Presenter.Month";

    }

    interface View extends IView<Presenter> {
        void showMonth(MonthEntity monthEntity);
    }
}
