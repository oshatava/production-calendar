package com.osh.mvp.presenter;


/**
 * Created by oleg on 2/9/2017.
 */

public interface HasPresenter<PresenterClass> {
    PresenterClass getPresenter();
    void setPresenter(PresenterClass presenter);
}
