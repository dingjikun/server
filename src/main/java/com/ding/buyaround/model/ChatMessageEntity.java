package com.ding.buyaround.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by djk on 2017/7/24.
 */
@Entity
@Table(name = "chatmessage")
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    private Long senderid;
    private String content;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createtime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderid() {
        return senderid;
    }

    public void setSenderid(Long senderid) {
        this.senderid = senderid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
