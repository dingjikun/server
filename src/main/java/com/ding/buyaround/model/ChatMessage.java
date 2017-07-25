package com.ding.buyaround.model;

/**
 * Created by djk on 2017/7/23.
 */
public class ChatMessage {
    private String msg;
    private String sourceuserid;

    public String getSourceuserid() {
        return sourceuserid;
    }

    public void setSourceuserid(String sourceuserid) {
        this.sourceuserid = sourceuserid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
