package com.osh.prodcal.common.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.osh.prodcal.common.application.HasAppComponent;

/**
 * Created by oleg on 2/7/2017.
 */

public abstract class AbstractActivity<T> extends AppCompatActivity implements HasAppComponent<T>{

    protected final String TAG = getClass().getSimpleName();
    
    public void showWait() {}

    public void hideWait() {}

    public void showError(Throwable error) {
        error.printStackTrace();
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewResId());
        inject();
        init();
    }

    protected void init() {}


    abstract protected void inject();

    abstract protected int getViewResId();

}
