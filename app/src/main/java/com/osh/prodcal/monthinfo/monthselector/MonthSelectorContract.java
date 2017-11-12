package com.osh.prodcal.monthinfo.monthselector;

import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.mvp.presenter.IPresenter;
import com.osh.mvp.view.IView;

import java.util.List;

/**
 * Created by olegshatava on 10.11.17.
 */

public interface MonthSelectorContract {


    interface Presenter extends IPresenter<View> {

        void onCurrentMonthChanged(MonthKeyEntity current);

        void onShowFullYear();

    }

    interface View extends IView<Presenter> {

        void showMonths(MonthKeyEntity current, List<MonthKeyEntity> months);

        void updateInfo(MonthKeyEntity currentMonth);

    }
}
