package com.example.skbnetwork;

public class ModelAddWorkTypeToWorkMaster {

    String dMAWTTWMClientName;
    String dMAWTTWMCSiteName;
    String dMAWTTWMCWorkTypeName;
    String dMAWTTWMCDateStamp;
    String dMAWTTWMCClient_SiteName;
    String dMAWTTWMCClient_Site_WorkType;

    public ModelAddWorkTypeToWorkMaster() {
    }

    public ModelAddWorkTypeToWorkMaster(String dMAWTTWMClientName, String dMAWTTWMCSiteName, String dMAWTTWMCWorkTypeName,
                                        String dMAWTTWMCDateStamp, String dMAWTTWMCClient_SiteName,
                                        String dMAWTTWMCClient_Site_WorkType) {
        this.dMAWTTWMClientName = dMAWTTWMClientName;
        this.dMAWTTWMCSiteName = dMAWTTWMCSiteName;
        this.dMAWTTWMCWorkTypeName = dMAWTTWMCWorkTypeName;
        this.dMAWTTWMCDateStamp = dMAWTTWMCDateStamp;
        this.dMAWTTWMCClient_SiteName = dMAWTTWMCClient_SiteName;
        this.dMAWTTWMCClient_Site_WorkType = dMAWTTWMCClient_Site_WorkType;
    }

    @Override
    public String toString() {
        return "ModelAddWorkTypeToWorkMaster{" +
                "dMAWTTWMClientName='" + dMAWTTWMClientName + '\'' +
                ", dMAWTTWMCSiteName='" + dMAWTTWMCSiteName + '\'' +
                ", dMAWTTWMCWorkTypeName='" + dMAWTTWMCWorkTypeName + '\'' +
                ", dMAWTTWMCDateStamp='" + dMAWTTWMCDateStamp + '\'' +
                ", dMAWTTWMCClient_SiteName='" + dMAWTTWMCClient_SiteName + '\'' +
                ", dMAWTTWMCClient_Site_WorkType='" + dMAWTTWMCClient_Site_WorkType + '\'' +
                '}';
    }

    public String getdMAWTTWMClientName() {
        return dMAWTTWMClientName;
    }

    public void setdMAWTTWMClientName(String dMAWTTWMClientName) {
        this.dMAWTTWMClientName = dMAWTTWMClientName;
    }

    public String getdMAWTTWMCSiteName() {
        return dMAWTTWMCSiteName;
    }

    public void setdMAWTTWMCSiteName(String dMAWTTWMCSiteName) {
        this.dMAWTTWMCSiteName = dMAWTTWMCSiteName;
    }

    public String getdMAWTTWMCWorkTypeName() {
        return dMAWTTWMCWorkTypeName;
    }

    public void setdMAWTTWMCWorkTypeName(String dMAWTTWMCWorkTypeName) {
        this.dMAWTTWMCWorkTypeName = dMAWTTWMCWorkTypeName;
    }

    public String getdMAWTTWMCDateStamp() {
        return dMAWTTWMCDateStamp;
    }

    public void setdMAWTTWMCDateStamp(String dMAWTTWMCDateStamp) {
        this.dMAWTTWMCDateStamp = dMAWTTWMCDateStamp;
    }

    public String getdMAWTTWMCClient_SiteName() {
        return dMAWTTWMCClient_SiteName;
    }

    public void setdMAWTTWMCClient_SiteName(String dMAWTTWMCClient_SiteName) {
        this.dMAWTTWMCClient_SiteName = dMAWTTWMCClient_SiteName;
    }

    public String getdMAWTTWMCClient_Site_WorkType() {
        return dMAWTTWMCClient_Site_WorkType;
    }

    public void setdMAWTTWMCClient_Site_WorkType(String dMAWTTWMCClient_Site_WorkType) {
        this.dMAWTTWMCClient_Site_WorkType = dMAWTTWMCClient_Site_WorkType;
    }
}
