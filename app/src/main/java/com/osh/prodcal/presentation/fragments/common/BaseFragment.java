package com.osh.prodcal.presentation.fragments.common;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osh.android.view.RequiredFragmentManager;
import com.osh.mvp.presenter.HasPresenter;
import com.osh.mvp.presenter.Presenter;
import com.osh.prodcal.application.PCApplication;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.presentation.activities.BaseActivity;

/**
 * Created by olegshatava on 23.10.17.
 */

public abstract class BaseFragment<ViewClass extends HasPresenter<PresenterClass>, PresenterClass extends Presenter<ViewClass>>
        extends com.osh.android.fragments.BaseFragment<PresenterClass> {

    public BaseFragment() {}

    abstract protected void inject(AppComponent appComponent);
    abstract protected int getViewResId();


    @SuppressWarnings("unchecked")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewClass fragmentView = (ViewClass)inflater.inflate(getViewResId(), container, false);
        getPresenter().setView(fragmentView);
        fragmentView.setPresenter(getPresenter());
        if(fragmentView instanceof RequiredFragmentManager){
            ((RequiredFragmentManager) fragmentView).setFragmentManager(getChildFragmentManager());
        }
        return (View)fragmentView;
    }


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
