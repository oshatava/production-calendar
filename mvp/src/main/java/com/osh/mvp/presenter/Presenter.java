package com.osh.mvp.presenter;

/**
 * Created by oleg on 2/9/2017.
 */

public interface Presenter <ViewClass>{
    void setView(ViewClass view);

    void onStart();
    void onPause();
    void onStop();
    void onDestroy();

    void onError(Throwable throwable);


}
