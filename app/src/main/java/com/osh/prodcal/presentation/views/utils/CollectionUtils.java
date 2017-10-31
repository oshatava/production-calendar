package com.osh.prodcal.presentation.views.utils;

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
