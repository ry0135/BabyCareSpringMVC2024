package com.example.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "Message") // Tên bảng trong cơ sở dữ liệu
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    @Column(name = "toId") // Tên cột trong cơ sở dữ liệu
    private String toId;
    @Column(name = "fromId") // Tên cột trong cơ sở dữ liệu

    private String fromId;
    @Column(name = "messageText") // Tên cột trong cơ sở dữ liệu
    @Lob
    private String messageText;
    @Column(name = "timestamp") // Tên cột trong cơ sở dữ liệu
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;


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


}

