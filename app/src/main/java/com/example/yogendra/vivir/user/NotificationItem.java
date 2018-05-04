package com.example.yogendra.vivir.user;

/**
 * Created by yogendra on 3/5/18.
 */

public class NotificationItem {

    private String nid;
    private String content;
    private String ndate;
    private int icon;

    public NotificationItem(String nid, String content, String ndate, int icon) {
        this.nid = nid;
        this.content = content;
        this.ndate = ndate;
        this.icon = icon;
    }

    //Getters

    public String getNid() {
        return nid;
    }

    public String getContent() {
        return content;
    }

    public String getNdate() {
        return ndate;
    }

    public int getIcon() {
        return icon;
    }

    //Setters


    public void setNid(String nid) {
        this.nid = nid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNdate(String ndate) {
        this.ndate = ndate;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
