package com.osh.zedsampleapp.common.presentation.view;

/**
 * Created by oleg on 2/9/2017.
 */

public interface DataView<EntityClass> extends View{
    void showData(EntityClass data);
}
