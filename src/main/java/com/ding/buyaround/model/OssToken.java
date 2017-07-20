package com.ding.buyaround.model;

/**
 * Created by pc on 2017/6/26.
 */
public class OssToken {
    private int code;
    private String accessKeyID;
    private String accessKeySecret;
    private String token;
    private String expiration;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAccessKeyID() {
        return accessKeyID;
    }

    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
