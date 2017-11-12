package com.osh.prodcal.yearlist.monthcalendar;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.osh.android.view.BaseView;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.R;
import com.osh.prodcal.widgets.MonthCalendarWidget;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthCalendarView extends BaseView<MonthCalendarContract.Presenter> implements MonthCalendarContract.View {

    private MonthCalendarWidget calendarWidget;

    public MonthCalendarView(@NonNull Context context) {
        super(context);
    }

    public MonthCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MonthCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean onInitView() {
        calendarWidget = (MonthCalendarWidget) findViewById(R.id.calendarView);
        return calendarWidget!=null;
    }

    @Override
    public void showMonth(MonthEntity monthEntity) {
        if(calendarWidget !=null)
            calendarWidget.showData(monthEntity);
    }

}
