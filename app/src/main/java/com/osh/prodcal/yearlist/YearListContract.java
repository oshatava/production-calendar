package com.osh.prodcal.yearlist;

import com.osh.mvp.presenter.IPresenter;
import com.osh.mvp.view.IView;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;

import java.util.List;

/**
 * Created by olegshatava on 10.11.17.
 */

public interface YearListContract {

    interface View extends IView<Presenter> {
        void showMonths(MonthKeyEntity current, List<MonthEntity> months);
    }

    interface Presenter extends IPresenter<View> {

        void onSelectMonth(MonthKeyEntity current);

        void onCancel();
    }
}
