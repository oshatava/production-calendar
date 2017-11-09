package com.osh.android.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewUtils {

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    public static <T> T findViewById(View parent, int id){
        if(parent!=null && id!=0){
            return (T)parent.findViewById(id);
        }
        return null;
    }


    public static <T> T findViewById(Activity parent, int id){
        if(parent!=null && id!=0){
            return (T)parent.findViewById(id);
        }
        return null;
    }

    public static <T extends View> T inflate(ViewGroup root, int layoutResId){
        LayoutInflater layoutInflater = LayoutInflater.from(root.getContext());
        T v = (T)layoutInflater.inflate(layoutResId, root, false);
        return v;
    }

    public static void hide(View view) {
        if(view!=null){
            view.setVisibility(View.GONE);
        }
    }

    public static void show(View view) {
        if(view!=null){
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void show(View root, int id) {
        View view = findViewById(root, id);
        if(view!=null){
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void show(Activity root, int id) {
        View view = findViewById(root, id);
        if(view!=null){
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hide(View root, int id) {
        View view = findViewById(root, id);
        if(view!=null){
            view.setVisibility(View.GONE);
        }
    }

    public static void hide(Activity root, int id) {
        View view = findViewById(root, id);
        if(view!=null){
            view.setVisibility(View.GONE);
        }
    }

    public static void hideSoft(View root, int id) {
        View view = findViewById(root, id);
        if(view!=null){
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void onClick(View v, View.OnClickListener clickListener) {
        if(v!=null)
            v.setOnClickListener(clickListener);
    }

    public static void onClick(View root, int id, View.OnClickListener clickListener) {
        View v = findViewById(root, id);
        onClick(v, clickListener);
    }

    public static void onClick(Activity root, int id, View.OnClickListener clickListener) {
        View v = findViewById(root, id);
        onClick(v, clickListener);
    }

    public static void text(View root, int id, String text) {
        TextView tv = findViewById(root, id);
        if(tv!=null)
            tv.setText(text);
    }

    public static void text(View root, int id, int textResId) {
        TextView tv = findViewById(root, id);
        if(tv!=null)
            tv.setText(textResId);
    }

    public static void text(Activity root, int id, String text) {
        TextView tv = findViewById(root, id);
        if(tv!=null)
            tv.setText(text);
    }

}
