package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "tblService")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceID")
    private int serviceID;

    @Column(name = "ServiceName")
    private String serviceName;

    @Column(name = "ServicePrice")
    private double servicePrice;
    @Column(name = "ServiceImage")
    private String listImg;
    @Column(name = "Description")
    private String description;

    @Column(name = "TypeID")
    private int typeId;
    @Column(name = "CTVID")
    private String cTVID;
    // Constructor, getter, setter và phương thức toString...
    public ServiceEntity() {}

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getListImg() {
        return listImg;
    }

    public void setListImg(String listImg) {
        this.listImg = listImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTypeID() {
        return typeId;
    }

    public void setTypeID(int typeID) {
        this.typeId = typeID;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getcTVID() {
        return cTVID;
    }

    public void setcTVID(String cTVID) {
        this.cTVID = cTVID;
    }
}
