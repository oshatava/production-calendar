package com.osh.zedsampleapp.presentation.views;

import android.content.Context;
import android.util.AttributeSet;

import com.osh.zedsampleapp.R;
import com.osh.zedsampleapp.common.presentation.view.BaseDataView;
import com.osh.zedsampleapp.data.dto.Holiday;
import com.osh.zedsampleapp.presentation.views.utils.ViewUtils;

public class TitleValueItemView extends BaseDataView<TitleValueItemView.Data> {


    public TitleValueItemView(Context context) {
        super(context);
    }

    public TitleValueItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleValueItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean onInitView() {
        return true;
    }

    @Override
    protected void onUpdateView(Data data) {
        ViewUtils.text(this, R.id.title, data.getTitle());
        ViewUtils.text(this, R.id.value, data.getValue());
    }

    public static class Data{
        private final String title;
        private final String value;

        public Data(String title, String value) {
            this.title = title;
            this.value = value;
        }

        public String getTitle() {
            return title;
        }

        public String getValue() {
            return value;
        }
    }
}
