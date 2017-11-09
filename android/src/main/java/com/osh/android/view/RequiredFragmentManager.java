package com.osh.android.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.osh.mvp.presenter.HasPresenter;
import com.osh.mvp.presenter.HasState;
import com.osh.mvp.presenter.Presenter;


/**
 * Created by olegshatava on 23.10.17.
 */

public interface RequiredFragmentManager{
    void setFragmentManager(FragmentManager fragmentManager);
}
