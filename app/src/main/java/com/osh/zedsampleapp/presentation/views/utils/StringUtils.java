package com.osh.zedsampleapp.presentation.views.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StringUtils {
    private static NumberFormat formatter_0dot0 = new DecimalFormat("#0.00");

    public static String toString(int value){
        return Integer.toString(value);
    }

    public static String toString(float value){
        return formatter_0dot0.format(value);
    }

}
