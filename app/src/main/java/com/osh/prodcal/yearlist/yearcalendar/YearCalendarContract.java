package com.osh.prodcal.yearlist.yearcalendar;

import com.osh.mvp.presenter.HasState;
import com.osh.mvp.presenter.IPresenter;
import com.osh.mvp.view.IView;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;

import java.util.List;

/**
 * Created by olegshatava on 10.11.17.
 */

public interface YearCalendarContract {

    interface View extends IView<Presenter> {
        void showYear(List<MonthEntity> monthEntities);
    }

    interface Presenter extends IPresenter<View>, HasState {
        String KEY_YEAR = "Presenter.YEAR";

        void onSelectMonth(MonthKeyEntity monthKeyEntity);
    }
}
