package com.example.model;

import java.time.LocalDateTime;
import java.util.Date;

public class ChatPartnerDTO {
    private String message;
    private Account account;
    private Date lastMessageTime;

    public ChatPartnerDTO() {
    }

    public ChatPartnerDTO(String message, Account account, Date lastMessageTime) {
        this.message = message;
        this.account = account;
        this.lastMessageTime = lastMessageTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(Date lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }
}
