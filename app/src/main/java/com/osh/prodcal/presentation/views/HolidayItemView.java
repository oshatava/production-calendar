package com.osh.prodcal.presentation.views;

import android.content.Context;
import android.util.AttributeSet;

import com.osh.prodcal.R;
import com.osh.prodcal.common.presentation.view.BaseDataView;
import com.osh.prodcal.data.dto.Holiday;
import com.osh.prodcal.presentation.views.utils.ViewUtils;

/**
 * Created by olegshatava on 24.10.17.
 */

public class HolidayItemView extends BaseDataView<Holiday> {


    public HolidayItemView(Context context) {
        super(context);
    }

    public HolidayItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HolidayItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean onInitView() {
        return true;
    }

    @Override
    protected void onUpdateView(Holiday data) {
        ViewUtils.text(this, R.id.text, data.getTitle());
    }
}
