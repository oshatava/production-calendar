package com.osh.prodcal.presentation.fragments.month;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osh.android.utils.ViewUtils;
import com.osh.prodcal.R;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.presentation.fragments.common.BaseFragment;
import com.osh.prodcal.presentation.presenters.MonthSelectorPresenter;
import com.osh.prodcal.presentation.views.MonthSelectorView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthSelectorFragment extends BaseFragment<MonthSelectorView, MonthSelectorPresenter> {

    public MonthSelectorFragment(){

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
    protected int getViewResId() {
        return R.layout.fragment_month_selector;
    }

}
