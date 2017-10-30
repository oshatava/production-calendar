package com.osh.prodcal.presentation.fragments.year;

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
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.presentation.fragments.common.BaseFragment;
import com.osh.prodcal.presentation.presenters.MonthListPresenter;
import com.osh.prodcal.presentation.views.MonthListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthListFragment extends BaseFragment<MonthListPresenter> implements MonthListView {

    private ViewPager pager;
    private MonthAdapter monthAdapter;

    public MonthListFragment(){
        setRetainInstance(true);
    }

    @Inject
    public MonthListPresenter presenter;

    @Override
    public MonthListPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_month_selector_full_year, container, false);
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
                //getPresenter().onCurrentMonthChanged(monthAdapter.getDataByIndex(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void showMonths(MonthEntity current, List<MonthEntity> months) {

        List<Integer> items = new ArrayList<>();

        Observable
                .just(months)
                .flatMapIterable(i->i)
                .flatMap(m->Observable.just(m.getMonthId()))
                .distinct()
                .subscribe(items::add);

        monthAdapter.setItems(items);
        pager.setCurrentItem(items.indexOf(current.getCurrentYear()), false);
    }


    public static class MonthAdapter extends FragmentStatePagerAdapter {

        private final List<Integer> items = new ArrayList<>();

        public MonthAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setItems(List<Integer> items) {
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
            return YearCalendarFragment.newInstance(items.get(position));
        }
    }

}
