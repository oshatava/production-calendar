package com.osh.prodcal.monthinfo.monthdetails.listitems;

import android.content.Context;
import android.util.AttributeSet;

import com.osh.android.utils.ViewUtils;
import com.osh.android.view.BaseDataView;
import com.osh.prodcal.R;

public class TitleValueItemView extends BaseDataView<TitleValueItemView.Listener, TitleValueItemView.Data> {


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
        if(data.isCanBeClicked() && getPresenter()!=null){
            ViewUtils.onClick(this, v->getPresenter().onClick(data));
        }
    }

    public static class Data{
        private final int layoutId;
        private final String title;
        private final String value;
        private final Object data;
        private final boolean canBeClicked;

        public Data(int layoutId, String title, String value) {
            this.layoutId = layoutId;
            this.title = title;
            this.value = value;
            this.data = null;
            this.canBeClicked = false;
        }

        public Data(int layoutId, String title) {
            this.layoutId = layoutId;
            this.title = title;
            this.value = null;
            this.data = null;
            this.canBeClicked = false;
        }

        public Data(int layoutId, String title, Object data) {
            this.layoutId = layoutId;
            this.title = title;
            this.value = null;
            this.data = data;
            this.canBeClicked = true;
        }

        public int getLayoutId() {
            return layoutId;
        }

        public String getTitle() {
            return title;
        }

        public String getValue() {
            return value;
        }

        public Object getData() {
            return data;
        }

        public boolean isCanBeClicked() {
            return canBeClicked;
        }
    }

    public interface Listener{
        void onClick(Data data);
    }
}
