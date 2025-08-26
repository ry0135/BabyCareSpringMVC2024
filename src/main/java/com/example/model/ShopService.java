package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblShopService")
public class ShopService {
        @Id
        @Column(name = "BrandID") // Tên cột trong cơ sở dữ liệu
        private String brandID;

    @Column(name = "BrandName") // Tên cột trong cơ sở dữ liệu
    private String brandName;

    @Column(name = "BrandDescription") // Tên cột trong cơ sở dữ liệu
    private String brandDescription;

    @Column(name = "BrandLogo") // Tên cột trong cơ sở dữ liệu
    private String brandLogo;

    @Column(name = "BrandAddress") // Tên cột trong cơ sở dữ liệu
    private String brandAddress;

    @Column(name = "CTVID") // Tên cột trong cơ sở dữ liệu
    private String ctvID;

    @Column(name = " BankName") // Tên cột trong cơ sở dữ liệu
    private String bankName;
    @Column(name = "AcountNumber") // Tên cột trong cơ sở dữ liệu
    private String acountNumber;
    @Column(name = "IdentifiNumber") // Tên cột trong cơ sở dữ liệu
    private String identifiNumber;
    @Column(name = "IdentifiImg") // Tên cột trong cơ sở dữ liệu
    private String identifiImg;
    @Column(name = "IdentifiImgFace") // Tên cột trong cơ sở dữ liệu
    private String identifiImgFace;
    @Column(name = "BrandPhone") // Tên cột trong cơ sở dữ liệu
    private String brandPhone;


    private int status;

    public ShopService() {
    }

    public ShopService(String brandID, String brandName, String brandDescription, String brandLogo, String brandAddress, String ctvID, String bandName, String eventType, String brandPhone, int status) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
        this.brandLogo = brandLogo;
        this.brandAddress = brandAddress;
        this.ctvID = ctvID;
        this.bankName = bandName;
        this.brandPhone = brandPhone;
        this.status = status;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBrandAddress() {
        return brandAddress;
    }

    public void setBrandAddress(String brandAddress) {
        this.brandAddress = brandAddress;
    }

    public String getCtvID() {
        return ctvID;
    }

    public void setCtvID(String ctvID) {
        this.ctvID = ctvID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


    public String getBrandPhone() {
        return brandPhone;
    }

    public void setBrandPhone(String brandPhone) {
        this.brandPhone = brandPhone;
    }


    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAcountNumber() {
        return acountNumber;
    }

    public void setAcountNumber(String acountNumber) {
        this.acountNumber = acountNumber;
    }

    public String getIdentifiNumber() {
        return identifiNumber;
    }

    public void setIdentifiNumber(String identifiNumber) {
        this.identifiNumber = identifiNumber;
    }

    public String getIdentifiImg() {
        return identifiImg;
    }

    public void setIdentifiImg(String identifiImg) {
        this.identifiImg = identifiImg;
    }

    public String getIdentifiImgFace() {
        return identifiImgFace;
    }

    public void setIdentifiImgFace(String identifiImgFace) {
        this.identifiImgFace = identifiImgFace;
    }


}
