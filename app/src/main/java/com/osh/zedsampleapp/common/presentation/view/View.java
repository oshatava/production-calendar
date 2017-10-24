package com.osh.zedsampleapp.common.presentation.view;

/**
 * Created by oleg on 2/9/2017.
 */

public interface View {
    void showWait();
    void hideWait();
    void showError(Throwable error);
}
