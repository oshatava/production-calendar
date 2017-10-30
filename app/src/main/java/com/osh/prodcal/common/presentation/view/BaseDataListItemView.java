package com.osh.prodcal.common.presentation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.osh.prodcal.R;
import com.osh.prodcal.presentation.views.utils.ViewUtils;

public abstract class BaseDataListItemView<EntityClass, ListenerClass> extends FrameLayout implements DataListItemView<EntityClass, ListenerClass> {
    private EntityClass data;
    private boolean viewIsCreated = false;
    private ListenerClass listener;

    /**
     * Init view
     * @return true if initialization is successful
     */
    protected abstract boolean onInitView();
    protected abstract void onUpdateView(EntityClass data);

    public BaseDataListItemView(Context context) {
        this(context, null);
    }

    public BaseDataListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseDataListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void setListener(ListenerClass listener) {
        this.listener = listener;
    }

    public ListenerClass getListener() {
        return listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    @Override
    public void showData(EntityClass data){
        this.data = data;
        updateView();
    }

    private void initView(){
        viewIsCreated = onInitView();
        updateView();
    }

    public void updateView(){
        if(!viewIsCreated || data == null)
            return;
        onUpdateView(data);
    }

    @Override
    public void hideWait() {
        ViewUtils.hide(this, R.id.wait);
    }

    @Override
    public void showWait() {
        ViewUtils.show(this, R.id.wait);
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
    }

}
