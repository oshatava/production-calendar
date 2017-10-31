package com.osh.prodcal.common.presentation.presenter;


import com.osh.prodcal.common.domain.usecase.UseCaseCollection;
import com.osh.prodcal.common.presentation.view.View;

import io.reactivex.disposables.Disposable;


public class BasePresenterComposite<ViewClass extends View> extends BasePresenter<UseCaseCollection, ViewClass>{
    public BasePresenterComposite(ViewClass view, Disposable ... useCases) {
        super(new UseCaseCollection(), view);
        for (Disposable useCase : useCases)
            getModel().put(useCase.getClass().toString(), useCase);
    }
}
