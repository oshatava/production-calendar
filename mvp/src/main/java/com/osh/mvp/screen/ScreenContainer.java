package com.osh.mvp.screen;

/**
 * Created by olegshatava on 01.11.17.
 */

public interface ScreenContainer {
    <T> T findView(int id);
}
