package com.example.skbnetwork;

public class ModelEntitlementDb {

    String name;
    String phNumber;
    String clientName;
    String siteNme;
    String menuName;
    String dateStamp;
    String Filler01;
    String Filler02;
    String Filler03;
    String Filler04;

    public ModelEntitlementDb() {
    }

    public ModelEntitlementDb(String name, String phNumber, String clientName, String siteNme,
                              String menuName, String dateStamp, String filler01, String filler02,
                              String filler03, String filler04) {
        this.name = name;
        this.phNumber = phNumber;
        this.clientName = clientName;
        this.siteNme = siteNme;
        this.menuName = menuName;
        this.dateStamp = dateStamp;
        Filler01 = filler01;
        Filler02 = filler02;
        Filler03 = filler03;
        Filler04 = filler04;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSiteNme() {
        return siteNme;
    }

    public void setSiteNme(String siteNme) {
        this.siteNme = siteNme;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getFiller01() {
        return Filler01;
    }

    public void setFiller01(String filler01) {
        Filler01 = filler01;
    }

    public String getFiller02() {
        return Filler02;
    }

    public void setFiller02(String filler02) {
        Filler02 = filler02;
    }

    public String getFiller03() {
        return Filler03;
    }

    public void setFiller03(String filler03) {
        Filler03 = filler03;
    }

    public String getFiller04() {
        return Filler04;
    }

    public void setFiller04(String filler04) {
        Filler04 = filler04;
    }
}
