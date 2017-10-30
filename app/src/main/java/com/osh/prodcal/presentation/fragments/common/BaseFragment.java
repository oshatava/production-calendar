package com.osh.prodcal.presentation.fragments.common;

import android.os.Bundle;
import android.util.Log;

import com.osh.prodcal.application.PCApplication;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.common.presentation.presenter.Presenter;
import com.osh.prodcal.presentation.activities.BaseActivity;

/**
 * Created by olegshatava on 23.10.17.
 */

public abstract class BaseFragment<PresenterClass extends Presenter>
        extends com.osh.prodcal.common.presentation.fragments.BaseFragment<PresenterClass> {

    public BaseFragment() {}

    abstract protected void inject(AppComponent appComponent);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        inject(PCApplication.getAppComponent(getActivity()));

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
