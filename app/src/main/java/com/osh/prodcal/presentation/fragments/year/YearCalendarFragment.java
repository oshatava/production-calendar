package com.osh.prodcal.presentation.fragments.year;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.presentation.fragments.common.BaseFragment;
import com.osh.prodcal.presentation.presenters.YearCalendarPresenter;
import com.osh.prodcal.presentation.views.YearCalendarView;
import com.osh.prodcal.presentation.views.utils.ViewUtils;
import com.osh.prodcal.presentation.views.widgets.MonthCalendarWidget;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class YearCalendarFragment extends BaseFragment<YearCalendarPresenter> implements YearCalendarView {

    static YearCalendarFragment newInstance(int year) {
        YearCalendarFragment f = new YearCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(YearCalendarPresenter.KEY_YEAR, year);
        f.setArguments(args);
        return f;
    }

    private SparseArray<MonthCalendarWidget> calendarWidgets = new SparseArray<>();

    public YearCalendarFragment(){
        setRetainInstance(true);
    }

    @Inject
    public YearCalendarPresenter presenter;

    @Override
    public YearCalendarPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_year_calendar, container, false);
        initView(fragmentView);
        getPresenter().setView(this);
        return fragmentView;
    }

    private void initView(View view){
        calendarWidgets.put(Calendar.JANUARY, ViewUtils.findViewById(view, R.id.month0));
        calendarWidgets.put(Calendar.FEBRUARY, ViewUtils.findViewById(view, R.id.month1));
        calendarWidgets.put(Calendar.MARCH, ViewUtils.findViewById(view, R.id.month2));
        calendarWidgets.put(Calendar.APRIL, ViewUtils.findViewById(view, R.id.month3));
        calendarWidgets.put(Calendar.MAY, ViewUtils.findViewById(view, R.id.month4));
        calendarWidgets.put(Calendar.JUNE, ViewUtils.findViewById(view, R.id.month5));
        calendarWidgets.put(Calendar.JULY, ViewUtils.findViewById(view, R.id.month6));
        calendarWidgets.put(Calendar.AUGUST, ViewUtils.findViewById(view, R.id.month7));
        calendarWidgets.put(Calendar.SEPTEMBER, ViewUtils.findViewById(view, R.id.month8));
        calendarWidgets.put(Calendar.OCTOBER, ViewUtils.findViewById(view, R.id.month9));
        calendarWidgets.put(Calendar.NOVEMBER, ViewUtils.findViewById(view, R.id.month10));
        calendarWidgets.put(Calendar.DECEMBER, ViewUtils.findViewById(view, R.id.month11));
    }

    @Override
    public void showYear(List<MonthEntity> monthEntities) {
        for(MonthEntity me:monthEntities){
            MonthCalendarWidget cv = calendarWidgets.get(me.getMonthId());
            if(cv!=null)
                cv.showData(me);
        }
    }
}
