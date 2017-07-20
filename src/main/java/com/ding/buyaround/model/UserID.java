package com.ding.buyaround.model;

/**
 * Created by djk on 2017/7/13.
 */
public class UserID {
    private Long userid;
    private String jwt;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
