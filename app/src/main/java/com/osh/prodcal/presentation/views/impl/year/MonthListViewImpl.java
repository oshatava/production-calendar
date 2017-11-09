package com.osh.prodcal.presentation.views.impl.year;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osh.android.utils.StringUtils;
import com.osh.android.utils.ViewUtils;
import com.osh.android.view.BaseView;
import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.fragments.common.BaseFragment;
import com.osh.prodcal.presentation.fragments.year.YearCalendarFragment;
import com.osh.prodcal.presentation.presenters.MonthListPresenter;
import com.osh.prodcal.presentation.views.MonthListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthListViewImpl extends BaseView<MonthListPresenter> implements MonthListView {

    private ViewPager pager;
    private MonthAdapter monthAdapter;

    public MonthListViewImpl(@NonNull Context context) {
        super(context);
    }

    public MonthListViewImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MonthListViewImpl(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean onInitView() {
        if(!hasFragmentManager()) return false;

        pager = ViewUtils.findViewById(this, R.id.pager);
        if(pager==null) return false;

        monthAdapter = new MonthAdapter(getFragmentManager());
        pager.setAdapter(monthAdapter);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                ViewUtils.text(MonthListViewImpl.this, R.id.yearTitle, StringUtils.toString(monthAdapter.getItemValue(position)));
            }
        });
        ViewUtils.onClick(this, R.id.back, v->getPresenter().onCancel());

        return true;
    }


    @Override
    public void showMonths(MonthKeyEntity current, List<MonthEntity> months) {

        List<Integer> items = new ArrayList<>();

        Observable
                .just(months)
                .flatMapIterable(i->i)
                .flatMap(m->Observable.just(m.getCurrentYear()))
                .distinct()
                .subscribe(items::add);

        monthAdapter.setItems(items);
        pager.setCurrentItem(items.indexOf(current.getYear()), false);
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

        public Integer getItemValue(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        @Override
        public Fragment getItem(int position) {
            return YearCalendarFragment.newInstance(getItemValue(position));
        }
    }

}
