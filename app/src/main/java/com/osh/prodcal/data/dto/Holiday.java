package com.osh.prodcal.data.dto;

/**
 * Created by olegshatava on 22.10.17.
 */

public class Holiday {
    private int id;
    private String title;

    public Holiday(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
