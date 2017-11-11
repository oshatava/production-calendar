package com.osh.prodcal.monthselector;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.osh.android.utils.ViewUtils;
import com.osh.android.view.BaseView;
import com.osh.prodcal.R;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.monthcalendar.MonthCalendarFragment;
import com.osh.prodcal.monthselector.MonthSelectorContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthSelectorView extends BaseView<MonthSelectorContract.Presenter> implements MonthSelectorContract.View {

    private ViewPager pager;
    private MonthAdapter monthAdapter;

    public MonthSelectorView(@NonNull Context context) {
        super(context);
    }

    public MonthSelectorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MonthSelectorView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected boolean onInitView() {
        if(!hasFragmentManager())
            return false;

        pager = ViewUtils.findViewById(this, R.id.pager);

        if(pager==null)
            return false;

        monthAdapter = new MonthAdapter(getFragmentManager());
        pager.setAdapter(monthAdapter);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                pager.post(() -> getPresenter().onCurrentMonthChanged(monthAdapter.getDataByIndex(position)));

            }
        });

        ViewUtils.onClick(this, R.id.showYearCalendar, v->{
            getPresenter().onShowFullYear();
        });

        return true;
    }

    @Override
    public void showMonths(MonthKeyEntity current, List<MonthKeyEntity> months) {
        monthAdapter.setItems(months);
        int index = months.indexOf(current);
        pager.setCurrentItem(index, false);
        ViewUtils.text(this, R.id.monthTitle, createTitleText(current));
    }

    @Override
    public void updateInfo(MonthKeyEntity current) {
        ViewUtils.text(this, R.id.monthTitle, createTitleText(current));
    }

    private String createTitleText(MonthKeyEntity current) {
        return getResources().getStringArray(R.array.month_names_full)[current.getMonth()]+", "+current.getYear();
    }

    public static class MonthAdapter extends FragmentStatePagerAdapter {

        private final List<MonthKeyEntity> items = new ArrayList<>();

        public MonthAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setItems(List<MonthKeyEntity> items) {
            this.items.clear();
            this.items.addAll(items);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        @Override
        public Fragment getItem(int position) {
            return MonthCalendarFragment.newInstance(items.get(position));
        }


        @Override
        public void finishUpdate(ViewGroup container) {
            try{
                super.finishUpdate(container);
            } catch (NullPointerException nullPointerException){
                nullPointerException.printStackTrace();
                System.out.println("Catch the NullPointerException in MonthAdapter.finishUpdate");
            }
        }

        public MonthKeyEntity getDataByIndex(int position) {
            return items.get(position);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            //super.restoreState(state, loader);
        }
    }

}
