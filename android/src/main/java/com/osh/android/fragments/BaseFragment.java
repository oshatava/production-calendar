package com.osh.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.osh.mvp.presenter.HasPresenter;
import com.osh.mvp.presenter.HasState;
import com.osh.mvp.presenter.Presenter;


/**
 * Created by olegshatava on 23.10.17.
 */

public abstract class BaseFragment<PresenterClass extends Presenter> extends Fragment
        implements HasPresenter<PresenterClass> {

    protected final String TAG = getClass().getSimpleName();

    public BaseFragment() {}

    @Override
    public void setPresenter(PresenterClass presenter) {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getPresenter() instanceof HasState){
            if(savedInstanceState!=null) {
                Log.d(TAG, "onActivityCreated: Restore from savedInstanceState "+savedInstanceState);
                ((HasState) getPresenter()).restoreState(savedInstanceState);
            }else if(getArguments()!=null) {
                Log.d(TAG, "onActivityCreated: Restore from getArguments "+getArguments());
                ((HasState) getPresenter()).restoreState(getArguments());
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getPresenter() instanceof HasState){
            ((HasState) getPresenter()).saveState(outState);
            Log.d(TAG, "onSaveInstanceState: Save instance to outState "+outState);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onPause");
        getPresenter().onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        getPresenter().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        getPresenter().onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        getPresenter().onDestroy();
    }
}
