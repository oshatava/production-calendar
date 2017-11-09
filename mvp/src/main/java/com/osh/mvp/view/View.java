package com.osh.mvp.view;

import com.osh.mvp.presenter.HasPresenter;

/**
 * Created by oleg on 2/9/2017.
 */

public interface View<PresenterClass> extends HasPresenter<PresenterClass>{
    void showWait();
    void hideWait();
    void showError(Throwable error);
    boolean isValid();
}
