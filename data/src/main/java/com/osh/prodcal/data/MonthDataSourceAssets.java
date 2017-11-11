package com.osh.prodcal.data;

import android.content.Context;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.osh.prodcal.domain.dto.Holiday;
import com.osh.prodcal.domain.dto.Month;
import com.osh.prodcal.domain.dto.MonthKey;
import com.osh.prodcal.domain.dto.Year;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthDataSourceAssets implements MonthDataSource{
    public static final String TAG = "MonthDataSource";

    private Object lock = new Object();

    private WeakReference<Context> context;

    private SparseArray<Year> cache;

    public MonthDataSourceAssets(Context context) {
        this.context = new WeakReference<Context>(context);
        initCache();
    }

    @Override
    public Month getMonth(int year, int month) {
        Year yr = cache.get(year);
        if (yr != null) {
            for (Month m : yr.getMonthData())
                if (m.getId() == month)
                    return m;
        }

        return new Month(month, new ArrayList<>());
    }

    private void initCache() {
        cache = new SparseArray<>();
        String data = AssetsHelper.getTxtFile(context.get().getAssets(), "data.json");
        Gson gson = new Gson();
        Type listYearsType = new TypeToken<ArrayList<Year>>() {
        }.getType();
        List<Year> years = gson.fromJson(data, listYearsType);
        for (Year year : years) {
            cache.put(year.getId(), year);
        }
    }

    @Override
    public List<Holiday> getHolidays(int year) {
        List<Holiday> ret = new ArrayList<>();
        Year yr = cache.get(year);
        if(yr!=null) {
            if(yr.getHolidays()!=null)
                ret.addAll(yr.getHolidays());
        }

        return ret;
    }

    @Override
    public List<MonthKey> getMonthIds() {
        List<MonthKey> ret = new ArrayList<>();

        for(int i=0; i<cache.size(); i++){
            int yearId = cache.keyAt(i);
            for(int monthId=0; monthId<12; monthId++){
                ret.add(new MonthKey(yearId, monthId));
            }
        }

        return ret;
    }
}
