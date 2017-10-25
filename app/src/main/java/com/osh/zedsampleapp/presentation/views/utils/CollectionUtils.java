package com.osh.zedsampleapp.presentation.views.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osh.zedsampleapp.common.presentation.view.BaseDataView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {

    public static <T> List<T> list(T ... items){
        List<T> ret = new ArrayList<>();
        Collections.addAll(ret, items);
        return ret;
    }

}
