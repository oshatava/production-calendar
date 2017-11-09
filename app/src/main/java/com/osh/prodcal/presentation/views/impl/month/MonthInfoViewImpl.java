package com.osh.prodcal.presentation.views.impl.month;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.osh.android.adapter.ViewEntityAdapter;
import com.osh.android.utils.CollectionUtils;
import com.osh.android.utils.StringUtils;
import com.osh.android.utils.ViewUtils;
import com.osh.android.view.BaseView;
import com.osh.prodcal.R;
import com.osh.prodcal.data.dto.Holiday;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.presentation.presenters.MonthInfoPresenter;
import com.osh.prodcal.presentation.views.MonthInfoView;
import com.osh.prodcal.presentation.views.listitems.TitleValueItemView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthInfoViewImpl extends BaseView<MonthInfoPresenter> implements MonthInfoView {

    private RecyclerView list;
    private ViewEntityAdapter<TitleValueItemView, TitleValueItemView.Data, TitleValueItemView.Listener> adapter;

    public MonthInfoViewImpl(@NonNull Context context) {
        super(context);
    }

    public MonthInfoViewImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MonthInfoViewImpl(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean onInitView() {
        list = ViewUtils.findViewById(this, R.id.list);
        if(list==null)
            return false;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);
        adapter = new ViewEntityAdapter<>(TitleValueItemView.Data::getLayoutId, d -> {
            if(d.getData() instanceof Holiday)
                getPresenter().onHolidayClicked((Holiday) d.getData());
        });
        list.setAdapter(adapter);

        return true;
    }

    @Override
    public void showMonth(MonthEntity monthEntity) {

        List<TitleValueItemView.Data> dataList = CollectionUtils.list(
                new TitleValueItemView.Data(R.layout.view_title_item, getString(R.string.title_number_of_days)),
                new TitleValueItemView.Data(R.layout.view_title_value_item, getString(R.string.title_number_of_days_total), StringUtils.toString(monthEntity.getNumberOfDay())),
                new TitleValueItemView.Data(R.layout.view_title_value_item, getString(R.string.title_number_of_working_days), StringUtils.toString(monthEntity.getNumberOfWorkingDay())),
                new TitleValueItemView.Data(R.layout.view_title_value_item, getString(R.string.title_number_of_non_working_days), StringUtils.toString(monthEntity.getNumberOfNonWorkingDay())),

                new TitleValueItemView.Data(R.layout.view_title_item, getString(R.string.title_number_of_working_hours)),
                new TitleValueItemView.Data(R.layout.view_title_value_item, getString(R.string.title_number_of_working_hours_40h), StringUtils.toString(monthEntity.getNumberOfHoursForWorkWeek(40))),
                new TitleValueItemView.Data(R.layout.view_title_value_item, getString(R.string.title_number_of_working_hours_36h), StringUtils.toString(monthEntity.getNumberOfHoursForWorkWeek(36))),
                new TitleValueItemView.Data(R.layout.view_title_value_item, getString(R.string.title_number_of_working_hours_24h), StringUtils.toString(monthEntity.getNumberOfHoursForWorkWeek(24))),

                new TitleValueItemView.Data(R.layout.view_title_item, getString(R.string.title_other)),
                new TitleValueItemView.Data(R.layout.view_title_value_item, getString(R.string.title_month), StringUtils.toString(monthEntity.getCurrentMonth().get(Calendar.MONTH) + 1)),
                new TitleValueItemView.Data(R.layout.view_title_value_item, getString(R.string.title_quarter), StringUtils.toString(monthEntity.getCurrentQuarter())),
                new TitleValueItemView.Data(R.layout.view_title_value_item, getString(R.string.title_half_year), StringUtils.toString(monthEntity.getCurrentHalfYear())),
                new TitleValueItemView.Data(R.layout.view_title_value_item, getString(R.string.title_year), StringUtils.toString(monthEntity.getCurrentYear()))
        );

        if(monthEntity.getHolidays().size()>0){
            List<TitleValueItemView.Data> holidays = new ArrayList<>();
            holidays.add(new TitleValueItemView.Data(R.layout.view_title_item, getString(R.string.title_holidays)));
            for(Holiday holiday:monthEntity.getHolidays()){
                holidays.add(new TitleValueItemView.Data(R.layout.view_holiday_info_item, holiday.getTitle(), holiday));
            }
            dataList.addAll(holidays);
        }
        adapter.setItems(dataList);
    }

}
