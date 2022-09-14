package com.example.skbnetwork;

public class ModelPurchaseRequest {

    String MPRdemandDate;
    String MPRclientSiteWorkType;
    String MPRitemDescription;
    int MPRpurchaseDemandQuantity;
    String MPRsiteFiller01;
    String MPRsiteFiller02;
    int MPRsiteFiller03;

    public ModelPurchaseRequest() {
    }

    public ModelPurchaseRequest(String MPRdemandDate, String MPRclientSiteWorkType, 
                                String MPRitemDescription, int MPRpurchaseDemandQuantity, String MPRsiteFiller01, 
                                String MPRsiteFiller02, int MPRsiteFiller03) {
        this.MPRdemandDate = MPRdemandDate;
        this.MPRclientSiteWorkType = MPRclientSiteWorkType;
        this.MPRitemDescription = MPRitemDescription;
        this.MPRpurchaseDemandQuantity = MPRpurchaseDemandQuantity;
        this.MPRsiteFiller01 = MPRsiteFiller01;
        this.MPRsiteFiller02 = MPRsiteFiller02;
        this.MPRsiteFiller03 = MPRsiteFiller03;
    }

    public String getMPRdemandDate() {
        return MPRdemandDate;
    }

    public void setMPRdemandDate(String MPRdemandDate) {
        this.MPRdemandDate = MPRdemandDate;
    }

    public String getMPRclientSiteWorkType() {
        return MPRclientSiteWorkType;
    }

    public void setMPRclientSiteWorkType(String MPRclientSiteWorkType) {
        this.MPRclientSiteWorkType = MPRclientSiteWorkType;
    }

    public String getMPRitemDescription() {
        return MPRitemDescription;
    }

    public void setMPRitemDescription(String MPRitemDescription) {
        this.MPRitemDescription = MPRitemDescription;
    }

    public int getMPRpurchaseDemandQuantity() {
        return MPRpurchaseDemandQuantity;
    }

    public void setMPRpurchaseDemandQuantity(int MPRpurchaseDemandQuantity) {
        this.MPRpurchaseDemandQuantity = MPRpurchaseDemandQuantity;
    }

    public String getMPRsiteFiller01() {
        return MPRsiteFiller01;
    }

    public void setMPRsiteFiller01(String MPRsiteFiller01) {
        this.MPRsiteFiller01 = MPRsiteFiller01;
    }

    public String getMPRsiteFiller02() {
        return MPRsiteFiller02;
    }

    public void setMPRsiteFiller02(String MPRsiteFiller02) {
        this.MPRsiteFiller02 = MPRsiteFiller02;
    }

    public int getMPRsiteFiller03() {
        return MPRsiteFiller03;
    }

    public void setMPRsiteFiller03(int MPRsiteFiller03) {
        this.MPRsiteFiller03 = MPRsiteFiller03;
    }
}
