package com.example.yogendra.vivir.tenant;

/**
 * Created by yogendra on 29/3/18.
 */

public class SearchItem {
    private String name;
    private String id;
    private String locality;
    private String city;
    private String imageURL;

    public SearchItem(String id,String name, String locality, String city, String imageURL) {
        this.id = id;
        this.name = name;
        this.locality = locality;
        this.city = city;
        this.imageURL = imageURL;
    }

    public String getName() {
        name = name +", " + locality+", "+city;
        return name;
    }

    public String getId() {
        return id;
    }
/*
    public String getLocality() {
        return locality;
    }

    public String getCity() {
        return city;
    }
    */

    public String getPicture() {
        imageURL = "https://vivir18.000webhostapp.com/vivir/media/"+imageURL;
        return imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setCity(String city) {
        this.city = city;
    }*/

    public void setPicture(String picture) {
        this.imageURL = picture;
    }
}
