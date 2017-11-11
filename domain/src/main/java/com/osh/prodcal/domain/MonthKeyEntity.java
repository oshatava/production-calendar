package com.osh.prodcal.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by olegshatava on 24.10.17.
 */

public class MonthKeyEntity implements Parcelable, Comparable{
    private final int year;
    private final int month;

    public MonthKeyEntity(int year, int month) {
        this.year = year;
        this.month = month;
    }

    protected MonthKeyEntity(Parcel in) {
        year = in.readInt();
        month = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(year);
        dest.writeInt(month);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MonthKeyEntity> CREATOR = new Creator<MonthKeyEntity>() {
        @Override
        public MonthKeyEntity createFromParcel(Parcel in) {
            return new MonthKeyEntity(in);
        }

        @Override
        public MonthKeyEntity[] newArray(int size) {
            return new MonthKeyEntity[size];
        }
    };

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthKeyEntity that = (MonthKeyEntity) o;

        if (year != that.year) return false;
        return month == that.month;

    }

    @Override
    public int hashCode() {
        int result = month;
        result = 12 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"@"+Integer.toHexString(hashCode())+" year="+year+" month="+month;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        if(o instanceof MonthKeyEntity)
            return Integer.valueOf(hashCode()).compareTo(o.hashCode());
        return 0;
    }
}
