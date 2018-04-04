package com.example.yogendra.vivir;

/**
 * Created by yogendra on 4/4/18.
 */

public class ComplaintValue {
    private String name;
    private int picture;
    private String cdate;

    public ComplaintValue(String name, int picture, String cdate) {
        this.name = name;
        this.picture = picture;

    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
