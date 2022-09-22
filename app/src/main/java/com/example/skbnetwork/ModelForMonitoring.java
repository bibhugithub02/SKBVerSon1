package com.example.skbnetwork;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModelForMonitoring {

    String systemDate;
    String menuOptionName;
    String siteDetails;
    String dbName;
    String actionDetails;
    String InformationDetails;

    public ModelForMonitoring() {
    }

    public ModelForMonitoring(String systemDate,
                              String menuOptionName, String siteDetails, String dbName, String actionDetails,
                              String informationDetails) {
        this.systemDate = systemDate;
        this.menuOptionName = menuOptionName;
        this.siteDetails = siteDetails;
        this.dbName = dbName;
        this.actionDetails = actionDetails;
        InformationDetails = informationDetails;
    }

    public String getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(String systemDate) {
        this.systemDate = systemDate;
    }

    public String getMenuOptionName() {
        return menuOptionName;
    }

    public void setMenuOptionName(String menuOptionName) {
        this.menuOptionName = menuOptionName;
    }

    public String getSiteDetails() {
        return siteDetails;
    }

    public void setSiteDetails(String siteDetails) {
        this.siteDetails = siteDetails;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getActionDetails() {
        return actionDetails;
    }

    public void setActionDetails(String actionDetails) {
        this.actionDetails = actionDetails;
    }

    public String getInformationDetails() {
        return InformationDetails;
    }

    public void setInformationDetails(String informationDetails) {
        InformationDetails = informationDetails;
    }


    @Override
    public String toString() {
        return "ModelForMonitoring{" +
                "systemDate='" + systemDate + '\'' +
                ", menuOptionName='" + menuOptionName + '\'' +
                ", siteDetails='" + siteDetails + '\'' +
                ", dbName='" + dbName + '\'' +
                ", actionDetails='" + actionDetails + '\'' +
                ", InformationDetails='" + InformationDetails + '\'' +
                '}';
    }

    public void writeToMonioringDB(String systemDate,
                          String menuOptionName, String siteDetails, String dbName, String actionDetails,
                          String informationDetails){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbr = firebaseDatabase.getReference("dModelForMonitoring");

        String dbsystemDate = systemDate;
        String dbmenuOptionName = menuOptionName;
        String dbsiteDetails = siteDetails;
        String dbdbName = dbName;
        String dbactionDetails = actionDetails;
        String dbinformationDetails = informationDetails;
        String searchKey = systemDate+"_"+ siteDetails+ "_" + dbName;

        ModelForMonitoring obj = new ModelForMonitoring(dbsystemDate,
                dbmenuOptionName, dbsiteDetails, dbdbName, dbactionDetails,
                dbinformationDetails);

        dbr.child(searchKey).setValue(obj);


    }

}
