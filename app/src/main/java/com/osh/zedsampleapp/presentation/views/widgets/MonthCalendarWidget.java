package com.osh.zedsampleapp.presentation.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.osh.zedsampleapp.R;
import com.osh.zedsampleapp.common.presentation.view.BaseDataView;
import com.osh.zedsampleapp.data.dto.Day;
import com.osh.zedsampleapp.domain.MonthEntity;
import com.osh.zedsampleapp.presentation.views.utils.ViewUtils;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by olegshatava on 24.10.17.
 */

public class MonthCalendarWidget extends BaseDataView<MonthEntity> {

    private static final int DEFAULT_TEXT_SIZE = 12;
    private Calendar calendar = Calendar.getInstance();

    private int textSizeSP;
    private int weekTitleTextColor;
    private int workingDayTextColor;
    private int nonWorkingDayTextColor;
    private int workingNonFullDayTextColor;
    private int nonWorkingDayBgColor;
    private int workingNonFullDayBgColor;

    private Paint headerTextPaint;
    private Paint headerCellPaint;

    private Paint workDayTextPaint;
    private Paint workDayCellPaint;

    private Paint nonWorkDayTextPaint;
    private Paint nonWorkDayCellPaint;

    private Paint nonFullWorkDayTextPaint;
    private Paint nonFullWorkDayCellPaint;

    private float cellCircleBorder = 0;

    public MonthCalendarWidget(Context context) {
        this(context, null);
    }

    public MonthCalendarWidget(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MonthCalendarWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        initPaint();
    }

    private void initPaint(){

        float textSize = ViewUtils.dpToPx(getContext(), textSizeSP);
        cellCircleBorder = ViewUtils.dpToPx(getContext(), 2);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        hasDayOffset = calendar.get(Calendar.WEEK_OF_MONTH) == 0;

        setWillNotDraw(false);

        headerTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        headerTextPaint.setColor(weekTitleTextColor);
        headerTextPaint.setTextAlign(Paint.Align.CENTER);
        headerTextPaint.setTextSize(textSize);

        headerCellPaint = null;


        workDayTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        workDayTextPaint.setColor(workingDayTextColor);
        workDayTextPaint.setTextSize(textSize);
        workDayTextPaint.setTextAlign(Paint.Align.CENTER);

        workDayCellPaint = null;

        nonWorkDayTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        nonWorkDayTextPaint.setColor(nonWorkingDayTextColor);
        nonWorkDayTextPaint.setTextSize(textSize);
        nonWorkDayTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        nonWorkDayTextPaint.setTextAlign(Paint.Align.CENTER);

        nonWorkDayCellPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        nonWorkDayCellPaint.setColor(nonWorkingDayBgColor);

        nonFullWorkDayTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        nonFullWorkDayTextPaint.setColor(workingNonFullDayTextColor);
        nonFullWorkDayTextPaint.setTextSize(textSize);
        nonFullWorkDayTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        nonFullWorkDayTextPaint.setTextAlign(Paint.Align.CENTER);

        nonFullWorkDayCellPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        nonFullWorkDayCellPaint.setColor(workingNonFullDayBgColor);

        if(isInEditMode())
            calendar = Calendar.getInstance();
    }

    private void initAttributes(Context context, AttributeSet attrs) {

        if (attrs!=null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MonthCalendarWidget, 0, 0);
            textSizeSP = a.getInteger(R.styleable.MonthCalendarWidget_textSize, DEFAULT_TEXT_SIZE);
            weekTitleTextColor = a.getColor(R.styleable.MonthCalendarWidget_weekTitleTextColor, Color.DKGRAY);
            workingDayTextColor = a.getColor(R.styleable.MonthCalendarWidget_workingDayTextColor, Color.BLACK);
            nonWorkingDayTextColor = a.getColor(R.styleable.MonthCalendarWidget_nonWorkingDayTextColor, Color.WHITE);
            workingNonFullDayTextColor = a.getColor(R.styleable.MonthCalendarWidget_workingNonFullDayTextColor, Color.BLACK);
            nonWorkingDayBgColor = a.getColor(R.styleable.MonthCalendarWidget_nonWorkingDayBgColor, Color.RED);
            workingNonFullDayBgColor = a.getColor(R.styleable.MonthCalendarWidget_workingNonFullDayBgColor, Color.YELLOW);
            a.recycle();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHeader(canvas);
        drawDays(canvas);
    }


    boolean hasDayOffset = false;

    private int getWeekOfMonth(Calendar calendar){
        return calendar.get(Calendar.WEEK_OF_MONTH) - (hasDayOffset?0:1);
    }

    private int dayOfWeekToIndex(Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int index = 0;


        if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY) {
            index = dayOfWeek - 1;
        } else {
            // monday
            index = dayOfWeek - 2;
            if (index < 0) index += 7;
        }

        return index;
    }

    private void drawDays(Canvas canvas) {
        for(int i=1; i<=calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            drawDay(canvas, i);
        }
    }

    private Calendar getCalendar(int dayNumber){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, this.calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, dayNumber);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 1);
        return calendar;
    }

    private void drawDay(Canvas canvas, int i) {
        Calendar calendar = getCalendar(i);
        int row = getWeekOfMonth(calendar)+1;
        int col = dayOfWeekToIndex(calendar);
        Day day = getDay(calendar.get(Calendar.DAY_OF_MONTH));
        if(day == null){
            day = new Day(i, -1, true, !(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY));
        }

        drawCellBg(canvas, getCellRect(col, row), getBgPaintForDay(day));
        drawTextLabel(canvas, getCellRect(col, row), Integer.toString(i), getTextPaintForDay(day));
    }

    private Paint getBgPaintForDay(Day day) {
        if(day.isWorked() && !day.isFull())
            return nonFullWorkDayCellPaint;
        if(day.isWorked() && day.isFull())
            return workDayCellPaint;
        if(!day.isWorked())
            return nonWorkDayCellPaint;

        return null;
    }

    private Paint getTextPaintForDay(Day day) {
        if(day.isWorked() && !day.isFull())
            return nonFullWorkDayTextPaint;
        if(day.isWorked() && day.isFull())
            return workDayTextPaint;
        if(!day.isWorked())
            return nonWorkDayTextPaint;

        return workDayTextPaint;
    }


    private Day getDay(int i) {
        if(getData()!=null){
            if(getData().getDays().containsKey(i)){
                return getData().getDays().get(i);
            }
        }
        return null;
    }

    private float getCellWidth() {
        return getMeasuredWidth() / 7f;
    }

    private float getCellHeight() {
        return getMeasuredHeight() / 7f;
    }

    private RectF getCellRect(int col, int row) {
        return new RectF(col * getCellWidth(),
                row * getCellHeight(),
                (col + 1) * getCellWidth(),
                (row + 1) * getCellHeight());
    }

    private void drawHeader(Canvas canvas) {
        for(int i=0; i<7; i++){
            drawCellBg(canvas, getCellRect(i, 0), headerCellPaint);
            drawTextLabel(canvas, getCellRect(i, 0), getShortDayLabelText(i), headerTextPaint);
        }
    }

    private void drawCellBg(Canvas canvas, RectF cellRect, Paint paint) {
        if (paint == null)
            return;
        canvas.drawCircle(cellRect.centerX(), cellRect.centerY(),
                Math.min(cellRect.width() / 2, cellRect.height() / 2) - cellCircleBorder,
                paint);
    }

    private void drawTextLabel(Canvas canvas, RectF cellRect, String text, Paint paint) {
        float xPos = cellRect.left +  (cellRect.width() / 2);
        float yPos = cellRect.top + (cellRect.height() / 2) - ((paint.descent() + paint.ascent()) / 2);
        canvas.drawText(text, xPos, yPos, paint);
    }

    private String getShortDayLabelText(int i) {
        return getResources().getStringArray(R.array.week_day_short_names)[i].toUpperCase();
    }

    @Override
    protected boolean onInitView() {
        return true;
    }

    @Override
    protected void onUpdateView(MonthEntity data) {
        calendar.setTimeInMillis(data.getCurrentMonth().getTimeInMillis());
        postInvalidate();
    }
}
