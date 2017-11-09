package com.osh.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.osh.mvp.view.DataView;

public abstract class BaseDataView<PresenterClass, EntityClass> extends BaseView<PresenterClass> implements DataView<PresenterClass, EntityClass> {
    private EntityClass data;

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
    public void showData(EntityClass data){
        this.data = data;
        if(isValid())
            updateView();
    }

    protected void initView(){
        super.initView();
        updateView();
    }

    public void updateView(){
        if(isValid() && hasData()) {
            onUpdateView(getData());
        }
    }

    protected boolean hasData(){
        return data!=null;
    }

    protected EntityClass getData() {
        return data;
    }
}
