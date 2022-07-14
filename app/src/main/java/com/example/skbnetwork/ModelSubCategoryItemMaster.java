package com.example.skbnetwork;

public class ModelSubCategoryItemMaster {

    String dMSCIMItemName;
    String dMSCIMItemUrl;
    String dMSCIMSubCategoryItemName;
    String dMSCIMDateStamp;
    String dMSCIMEnterBy;
    String dMSCIMProductBy;
    String dMSCIMDelete;

    public ModelSubCategoryItemMaster() {
    }

    public ModelSubCategoryItemMaster(String dMSCIMItemName, String dMSCIMItemUrl,
                                      String dMSCIMSubCategoryItemName, String dMSCIMDateStamp,
                                      String dMSCIMEnterBy, String dMSCIMProductBy, String dMSCIMDelete) {
        this.dMSCIMItemName = dMSCIMItemName;
        this.dMSCIMItemUrl = dMSCIMItemUrl;
        this.dMSCIMSubCategoryItemName = dMSCIMSubCategoryItemName;
        this.dMSCIMDateStamp = dMSCIMDateStamp;
        this.dMSCIMEnterBy = dMSCIMEnterBy;
        this.dMSCIMProductBy = dMSCIMProductBy;
        this.dMSCIMDelete = dMSCIMDelete;
    }

    @Override
    public String toString() {
        return "ModelSubCategoryItemMaster{" +
                "dMSCIMItemName='" + dMSCIMItemName + '\'' +
                ", dMSCIMItemUrl='" + dMSCIMItemUrl + '\'' +
                ", dMSCIMSubCategoryItemName='" + dMSCIMSubCategoryItemName + '\'' +
                ", dMSCIMDateStamp='" + dMSCIMDateStamp + '\'' +
                ", dMSCIMEnterBy='" + dMSCIMEnterBy + '\'' +
                ", dMSCIMProductBy='" + dMSCIMProductBy + '\'' +
                ", dMSCIMDelete='" + dMSCIMDelete + '\'' +
                '}';
    }

    public String getdMSCIMItemName() {
        return dMSCIMItemName;
    }

    public void setdMSCIMItemName(String dMSCIMItemName) {
        this.dMSCIMItemName = dMSCIMItemName;
    }

    public String getdMSCIMItemUrl() {
        return dMSCIMItemUrl;
    }

    public void setdMSCIMItemUrl(String dMSCIMItemUrl) {
        this.dMSCIMItemUrl = dMSCIMItemUrl;
    }

    public String getdMSCIMSubCategoryItemName() {
        return dMSCIMSubCategoryItemName;
    }

    public void setdMSCIMSubCategoryItemName(String dMSCIMSubCategoryItemName) {
        this.dMSCIMSubCategoryItemName = dMSCIMSubCategoryItemName;
    }

    public String getdMSCIMDateStamp() {
        return dMSCIMDateStamp;
    }

    public void setdMSCIMDateStamp(String dMSCIMDateStamp) {
        this.dMSCIMDateStamp = dMSCIMDateStamp;
    }

    public String getdMSCIMEnterBy() {
        return dMSCIMEnterBy;
    }

    public void setdMSCIMEnterBy(String dMSCIMEnterBy) {
        this.dMSCIMEnterBy = dMSCIMEnterBy;
    }

    public String getdMSCIMProductBy() {
        return dMSCIMProductBy;
    }

    public void setdMSCIMProductBy(String dMSCIMProductBy) {
        this.dMSCIMProductBy = dMSCIMProductBy;
    }

    public String getdMSCIMDelete() {
        return dMSCIMDelete;
    }

    public void setdMSCIMDelete(String dMSCIMDelete) {
        this.dMSCIMDelete = dMSCIMDelete;
    }
}
