package com.example.model;


import javax.persistence.*;
import java.util.Date;


public class MessageDTO {


    private int messageId;
    private String toId;

    private String fromId;


    private String messageText;


    private Date timestamp;


    private String avatar;

    // Getters và setters
    public int getMessageId() { return messageId; }
    public void setMessageId(int messageId) { this.messageId = messageId; }
    public String getToId() { return toId; }
    public void setToId(String toId) { this.toId = toId; }
    public String getFromId() { return fromId; }
    public void setFromId(String fromId) { this.fromId = fromId; }
    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

