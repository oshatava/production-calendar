package com.osh.prodcal.common.presentation.presenter;


import com.osh.prodcal.common.domain.usecase.UseCaseCollection;
import com.osh.prodcal.common.presentation.view.View;


public class BasePresenterComposite<ViewClass extends View> extends BasePresenter<UseCaseCollection, ViewClass>{

    public BasePresenterComposite(UseCaseCollection model, ViewClass view) {
        super(model, view);
    }

}
