package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "FavoriteService")
public class FavoriteService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteID")
    private Integer favoriteID;

    @Column(name = "UserID")
    private String userID;

    @Column(name = "ServiceID")
    private int serviceID;


    public FavoriteService() {
    }

    // Getters và Setters
    public Integer getFavoriteID() {
        return favoriteID;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }



}
