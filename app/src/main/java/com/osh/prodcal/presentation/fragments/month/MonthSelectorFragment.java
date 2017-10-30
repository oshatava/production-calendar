package com.osh.prodcal.presentation.fragments.month;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.fragments.common.BaseFragment;
import com.osh.prodcal.presentation.presenters.MonthSelectorPresenter;
import com.osh.prodcal.presentation.views.MonthSelectorView;
import com.osh.prodcal.presentation.views.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthSelectorFragment extends BaseFragment<MonthSelectorPresenter> implements MonthSelectorView {

    private ViewPager pager;
    private MonthAdapter monthAdapter;
    public MonthSelectorFragment(){
        setRetainInstance(true);
    }

    @Inject
    public MonthSelectorPresenter presenter;

    @Override
    public MonthSelectorPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_month_selector, container, false);
        initView(fragmentView);
        getPresenter().setView(this);
        return fragmentView;
    }

    private void initView(View view){
        pager = (ViewPager)view.findViewById(R.id.pager);
        monthAdapter = new MonthAdapter(getChildFragmentManager());
        pager.setAdapter(monthAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pager.post(() -> getPresenter().onCurrentMonthChanged(monthAdapter.getDataByIndex(position)));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ViewUtils.onClick(view, R.id.monthTitle, v->{
            getPresenter().onShowFullYear();
        });
    }

    @Override
    public void showMonths(MonthKeyEntity current, List<MonthKeyEntity> months) {
        monthAdapter.setItems(months);
        int index = months.indexOf(current);
        pager.setCurrentItem(index, false);
        ViewUtils.text(getView(), R.id.monthTitle, createTitleText(current));
    }

    @Override
    public void updateInfo(MonthKeyEntity current) {
        ViewUtils.text(getView(), R.id.monthTitle, createTitleText(current));
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
    }

}
