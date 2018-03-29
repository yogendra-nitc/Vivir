package com.example.yogendra.vivir;

/**
 * Created by yogendra on 29/3/18.
 */

public class SearchItem {
    private String name;
    private int picture;

    public SearchItem(String name, int picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
