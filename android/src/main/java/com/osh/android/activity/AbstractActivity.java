package com.osh.android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.osh.android.utils.ViewUtils;

/**
 * Created by oleg on 2/7/2017.
 */

public abstract class AbstractActivity<T> extends AppCompatActivity{

    protected final String TAG = getClass().getSimpleName();
    
    public void showWait() {}

    public void hideWait() {}

    public void showError(Throwable error) {
        error.printStackTrace();
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
    }

    protected View getRootView(){
        ViewGroup content = ViewUtils.findViewById(this, android.R.id.content);
        if(content!=null && content.getChildCount()>0) {
            return content.getChildAt(0);
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewResId());
        View v = getRootView();
        inject();
        init();
    }

    protected void init() {}


    abstract protected void inject();

    abstract protected int getViewResId();

}
