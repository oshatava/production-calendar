package com.osh.prodcal.common.presentation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Toast;

public abstract class BaseDataView<EntityClass> extends FrameLayout implements DataView<EntityClass> {
    private EntityClass data;
    private boolean viewIsCreated = false;

    /**
     * Init view
     * @return true if initialization is successful
     */
    protected abstract boolean onInitView();
    protected abstract void onUpdateView(EntityClass data);

    public BaseDataView(Context context) {
        this(context, null);
    }

    public BaseDataView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
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
    }

    @Override
    public void showWait() {
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
    }

    protected EntityClass getData() {
        return data;
    }
}
