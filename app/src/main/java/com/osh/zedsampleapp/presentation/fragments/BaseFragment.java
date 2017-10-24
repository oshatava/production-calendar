package com.osh.zedsampleapp.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osh.zedsampleapp.R;
import com.osh.zedsampleapp.application.ZedApplication;
import com.osh.zedsampleapp.application.di.AppComponent;
import com.osh.zedsampleapp.common.application.HasAppComponent;
import com.osh.zedsampleapp.common.presentation.presenter.HasPresenter;
import com.osh.zedsampleapp.common.presentation.presenter.HasState;
import com.osh.zedsampleapp.common.presentation.presenter.Presenter;
import com.osh.zedsampleapp.presentation.activities.BaseActivity;

/**
 * Created by olegshatava on 23.10.17.
 */

public abstract class BaseFragment<PresenterClass extends Presenter>
        extends com.osh.zedsampleapp.common.presentation.fragments.BaseFragment<PresenterClass> {

    public BaseFragment() {}

    abstract void inject(AppComponent appComponent);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        inject(ZedApplication.getAppComponent(getActivity()));

    }

    public void showWait() {
        if(getActivity()instanceof BaseActivity)
            ((BaseActivity) getActivity()).showWait();
    }

    public void hideWait() {
        if(getActivity()instanceof BaseActivity)
            ((BaseActivity) getActivity()).hideWait();
    }

    public void showError(Throwable error) {
        if(getActivity()instanceof BaseActivity)
            ((BaseActivity) getActivity()).showError(error);
    }
}
