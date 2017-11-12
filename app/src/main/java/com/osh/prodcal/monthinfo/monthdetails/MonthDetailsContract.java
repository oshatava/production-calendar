package com.osh.prodcal.monthinfo.monthdetails;

import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.dto.Holiday;
import com.osh.mvp.presenter.IPresenter;
import com.osh.mvp.view.IView;

/**
 * Created by olegshatava on 10.11.17.
 */

public interface MonthDetailsContract {

    interface Presenter extends IPresenter<View> {
        void onHolidayClicked(Holiday holiday);
    }

    interface View extends IView<Presenter> {
        void showMonth(MonthEntity monthEntity);
    }

}
