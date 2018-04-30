package com.example.yogendra.vivir.owner;

/**
 * Created by yogendra on 29/4/30.
 */


public class RequestItem  {
    private String rid;
    private String name;
    private String rtype;
    private String rdate;
    private int image;

    public RequestItem(String rid,String name, String rtype, String rdate,
                      int image) {
        this.rid = rid;
        this.name = name;
        this.rtype = rtype;
        this.rdate = rdate;
        this.image = image;
    }

    //Getters
    public String getId() {
        return rid;
    }

    public String getName() {
        return name;
    }

    public String getRtype() {
        return rtype;
    }

    public String getRdate() {
        return rdate;
    }

    public int getImage() {
        return image;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.rid = id;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
