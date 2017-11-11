package com.osh.android.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.osh.mvp.view.IView;

/**
 * Created by olegshatava on 23.10.17.
 */

public abstract class BaseView<PresenterClass> extends FrameLayout implements IView<PresenterClass>, RequiredFragmentManager {
    private boolean viewIsCreated = false;
    private FragmentManager fragmentManager;
    private PresenterClass presenter;

    protected abstract boolean onInitView();

    @Override
    public void setPresenter(PresenterClass presenter){
        this.presenter = presenter;
    }

    @Override
    public PresenterClass getPresenter() {
        return presenter;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    protected boolean hasFragmentManager(){
        return fragmentManager != null;
    }

    public BaseView(@NonNull Context context) {
        this(context, null);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView(){
        if(!viewIsCreated) {
            viewIsCreated = onInitView();
        }
    }

    @Override
    public boolean isValid() {
        return viewIsCreated;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    @Override
    public void showWait() {

    }

    @Override
    public void hideWait() {

    }

    @Override
    public void showError(Throwable error) {

    }

    protected String getString(int id){
        return getResources().getString(id);
    }
}
