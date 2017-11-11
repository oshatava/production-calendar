package com.osh.mvp.presenter;

import com.osh.mvp.view.IView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;


public class BasePresenter<ModelClass extends Disposable, ViewClass extends IView> implements IPresenter<ViewClass> {
    protected final String TAG = getClass().getSimpleName();

    private ModelClass model;
    private WeakReference<ViewClass> viewRef;


    public BasePresenter(ModelClass model, ViewClass view) {
        this.model = model;
        this.viewRef = new WeakReference<>(view);
    }

    public BasePresenter(ModelClass model) {
        this(model, null);
    }

    public ModelClass getModel() {
        return model;
    }

    public ViewClass getView() {
        return viewRef.get();
    }

    public boolean hasModel(){
        return model!=null;
    }

    public boolean hasView(){
        return viewRef!=null && viewRef.get()!=null && viewRef.get().isValid();
    }

    @Override
    public void setView(ViewClass view) {
        this.viewRef = new WeakReference<>(view);
    }

    @Override
    public void onStart() {}

    @Override
    public void onPause() {}

    @Override
    public void onStop() {}

    @Override
    public void onDestroy() {
        if(hasModel()) getModel().dispose();
    }

    @Override
    public void onError(Throwable throwable) {
        if(!hasView()) return;
        getView().hideWait();
        getView().showError(throwable);
    }

}
