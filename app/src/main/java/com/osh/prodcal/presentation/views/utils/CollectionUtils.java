package com.osh.prodcal.presentation.views.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {

    public static <T> List<T> list(Collection<T> items){
        List<T> ret = new ArrayList<>();
        ret.addAll(items);
        return ret;
    }

    public static <T> List<T> list(T ... items){
        List<T> ret = new ArrayList<>();
        Collections.addAll(ret, items);
        return ret;
    }

    public static <T, B> List<T> map(Collection<B> items, Mapper<T, B> mapper){
        List<T> ret = new ArrayList<>();
        for(B b:items)
            ret.add(mapper.map(b));
        return ret;
    }


    public interface Mapper<T, B> {
        T map(B i);
    }
}
