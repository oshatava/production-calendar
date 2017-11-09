package com.osh.prodcal.presentation.views.impl.year;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osh.android.utils.ViewUtils;
import com.osh.android.view.BaseView;
import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.fragments.common.BaseFragment;
import com.osh.prodcal.presentation.fragments.year.YearCalendarFragment;
import com.osh.prodcal.presentation.presenters.YearCalendarPresenter;
import com.osh.prodcal.presentation.views.YearCalendarView;
import com.osh.prodcal.presentation.views.widgets.MonthCalendarWidget;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class YearCalendarViewImpl extends BaseView<YearCalendarPresenter> implements YearCalendarView {

    private SparseArray<MonthCalendarWidget> calendarWidgets = new SparseArray<>();

    public YearCalendarViewImpl(@NonNull Context context) {
        super(context);
    }

    public YearCalendarViewImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YearCalendarViewImpl(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected boolean onInitView() {
        if(ViewUtils.findViewById(this, R.id.month0)==null)
            return false;

        calendarWidgets.put(Calendar.JANUARY, ViewUtils.findViewById(this, R.id.month0));
        calendarWidgets.put(Calendar.FEBRUARY, ViewUtils.findViewById(this, R.id.month1));
        calendarWidgets.put(Calendar.MARCH, ViewUtils.findViewById(this, R.id.month2));
        calendarWidgets.put(Calendar.APRIL, ViewUtils.findViewById(this, R.id.month3));
        calendarWidgets.put(Calendar.MAY, ViewUtils.findViewById(this, R.id.month4));
        calendarWidgets.put(Calendar.JUNE, ViewUtils.findViewById(this, R.id.month5));
        calendarWidgets.put(Calendar.JULY, ViewUtils.findViewById(this, R.id.month6));
        calendarWidgets.put(Calendar.AUGUST, ViewUtils.findViewById(this, R.id.month7));
        calendarWidgets.put(Calendar.SEPTEMBER, ViewUtils.findViewById(this, R.id.month8));
        calendarWidgets.put(Calendar.OCTOBER, ViewUtils.findViewById(this, R.id.month9));
        calendarWidgets.put(Calendar.NOVEMBER, ViewUtils.findViewById(this, R.id.month10));
        calendarWidgets.put(Calendar.DECEMBER, ViewUtils.findViewById(this, R.id.month11));

        return true;
    }

    @Override
    public void showYear(List<MonthEntity> monthEntities) {
        for(MonthEntity me:monthEntities){
            MonthCalendarWidget cv = calendarWidgets.get(me.getMonthId());
            if(cv!=null) {
                cv.showData(me);
                ViewUtils.onClick(cv, v->getPresenter().onSelectMonth(new MonthKeyEntity(me.getCurrentYear(), me.getMonthId())));
            }
        }
    }
}
