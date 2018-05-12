
package com.example.yogendra.vivir.user;

/**
 * Created by yogendra on 29/4/30.
 */


public class complainItem  {
    private String cid;
    private String sname;
    private String rname;
    private String cdate;

    public complainItem(String cid,String sname, String rname, String cdate
                       ) {
        this.cid = cid;
        this.sname = sname;
        this.rname = rname;
        this.cdate = cdate;
    }

    //Getters


    public String getCid() {
        return cid;
    }

    public String getSname() {
        return sname;
    }

    public String getRname() {
        return rname;
    }

    public String getCdate() {
        return cdate;
    }

    //Setters


    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
