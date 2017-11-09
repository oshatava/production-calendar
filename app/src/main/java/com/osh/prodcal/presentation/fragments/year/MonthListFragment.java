package com.osh.prodcal.presentation.fragments.year;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osh.android.utils.StringUtils;
import com.osh.android.utils.ViewUtils;
import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
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

public class MonthListFragment extends BaseFragment<MonthListView, MonthListPresenter>  {


    public MonthListFragment(){
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
    protected int getViewResId() {
        return R.layout.fragment_month_selector_full_year;
    }
}
