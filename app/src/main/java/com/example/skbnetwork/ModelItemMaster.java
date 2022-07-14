package com.example.skbnetwork;

public class ModelItemMaster {
    String dMIMItemName;
    String dMIMItemUrl;
    String dMIMEnterDate;
    String dMIMAddedBy;

    public ModelItemMaster() {
    }

    public ModelItemMaster(String dMIMItemName, String dMIMItemUrl, String dMIMEnterDate, String dMIMAddedBy) {
        this.dMIMItemName = dMIMItemName;
        this.dMIMItemUrl = dMIMItemUrl;
        this.dMIMEnterDate = dMIMEnterDate;
        this.dMIMAddedBy = dMIMAddedBy;
    }

    @Override
    public String toString() {
        return "ModelItemMaster{" +
                "dMIMItemName='" + dMIMItemName + '\'' +
                ", dMIMItemUrl='" + dMIMItemUrl + '\'' +
                ", dMIMEnterDate='" + dMIMEnterDate + '\'' +
                ", dMIMAddedBy='" + dMIMAddedBy + '\'' +
                '}';
    }

    public String getdMIMItemName() {
        return dMIMItemName;
    }

    public void setdMIMItemName(String dMIMItemName) {
        this.dMIMItemName = dMIMItemName;
    }

    public String getdMIMItemUrl() {
        return dMIMItemUrl;
    }

    public void setdMIMItemUrl(String dMIMItemUrl) {
        this.dMIMItemUrl = dMIMItemUrl;
    }

    public String getdMIMEnterDate() {
        return dMIMEnterDate;
    }

    public void setdMIMEnterDate(String dMIMEnterDate) {
        this.dMIMEnterDate = dMIMEnterDate;
    }

    public String getdMIMAddedBy() {
        return dMIMAddedBy;
    }

    public void setdMIMAddedBy(String dMIMAddedBy) {
        this.dMIMAddedBy = dMIMAddedBy;
    }
}


