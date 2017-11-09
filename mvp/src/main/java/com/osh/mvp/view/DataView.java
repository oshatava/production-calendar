package com.osh.mvp.view;

/**
 * Created by oleg on 2/9/2017.
 */

public interface DataView<PresenterClass, EntityClass> extends View<PresenterClass>{
    void showData(EntityClass data);
}
