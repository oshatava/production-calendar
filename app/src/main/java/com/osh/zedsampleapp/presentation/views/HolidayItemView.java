package com.osh.zedsampleapp.presentation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.osh.zedsampleapp.R;
import com.osh.zedsampleapp.common.presentation.view.BaseDataView;
import com.osh.zedsampleapp.data.dto.Day;
import com.osh.zedsampleapp.data.dto.Holiday;
import com.osh.zedsampleapp.domain.MonthEntity;
import com.osh.zedsampleapp.presentation.views.utils.ViewUtils;

import java.util.Calendar;

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
