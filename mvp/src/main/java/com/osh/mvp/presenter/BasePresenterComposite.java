package com.osh.mvp.presenter;


import com.osh.mvp.domain.usecase.UseCaseCollection;
import com.osh.mvp.view.IView;

import io.reactivex.disposables.Disposable;


public class BasePresenterComposite<ViewClass extends IView> extends BasePresenter<UseCaseCollection, ViewClass>{
    public BasePresenterComposite(ViewClass view, Disposable ... useCases) {
        super(new UseCaseCollection(), view);
        for (Disposable useCase : useCases)
            getModel().put(useCase.getClass().toString(), useCase);
    }

    public BasePresenterComposite(Disposable ... useCases) {
        this(null, useCases);
    }
}
