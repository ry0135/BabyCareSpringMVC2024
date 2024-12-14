package com.example.model;

public class FavoriteServiceDTO {
    private Integer favoriteID;
    private String userID;
    private int serviceID;
    private String serviceName;
    private double servicePrice;
    private String listImg;
    private String description;
    private int typeId;
    private String CTVID;

    // Constructor nhận hai đối tượng
    public FavoriteServiceDTO(FavoriteService favoriteService, ServiceEntity serviceEntity) {
        this.favoriteID = favoriteService.getFavoriteID();
        this.userID = favoriteService.getUserID();
        this.serviceID = serviceEntity.getServiceID();
        this.serviceName = serviceEntity.getServiceName();
        this.servicePrice = serviceEntity.getServicePrice();
        this.listImg = serviceEntity.getListImg();
        this.description = serviceEntity.getDescription();
        this.typeId = serviceEntity.getTypeId();
        this.CTVID = serviceEntity.getcTVID();
    }

    public Integer getFavoriteID() {
        return favoriteID;
    }

    public String getUserID() {
        return userID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public String getListImg() {
        return listImg;
    }

    public String getDescription() {
        return description;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getCTVID() {
        return CTVID;
    }
}
