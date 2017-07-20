package com.ding.buyaround.model;

/**
 * Created by djk on 2017/7/11.
 */
public class StockPOI {
    private String userID;
    private String jd;
    private String wd;
    private String categoryID;
    private String ossID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getOssID() {
        return ossID;
    }

    public void setOssID(String ossID) {
        this.ossID = ossID;
    }
}
