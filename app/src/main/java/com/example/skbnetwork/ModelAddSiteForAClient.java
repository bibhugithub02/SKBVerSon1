package com.example.skbnetwork;

public class ModelAddSiteForAClient {

    String dMTASFClientName;
    String dMTASFSiteName;
    String dMTASFWorkType;
    String dMTASFProjectType;
    String dMTASFTimeStamp;

    public ModelAddSiteForAClient() {
    }

    public ModelAddSiteForAClient(String dMTASFClientName, String dMTASFSiteName,
                                  String dMTASFWorkType, String dMTASFProjectType, String dMTASFTimeStamp) {
        this.dMTASFClientName = dMTASFClientName;
        this.dMTASFSiteName = dMTASFSiteName;
        this.dMTASFWorkType = dMTASFWorkType;
        this.dMTASFProjectType = dMTASFProjectType;
        this.dMTASFTimeStamp = dMTASFTimeStamp;
    }

    @Override
    public String toString() {
        return "ModelToAddSiteAForClient{" +
                "dMTASFClientName='" + dMTASFClientName + '\'' +
                ", dMTASFSiteName='" + dMTASFSiteName + '\'' +
                ", dMTASFWorkType='" + dMTASFWorkType + '\'' +
                ", dMTASFProjectType='" + dMTASFProjectType + '\'' +
                ", dMTASFTimeStamp='" + dMTASFTimeStamp + '\'' +
                '}';
    }

    public String getdMTASFClientName() {
        return dMTASFClientName;
    }

    public void setdMTASFClientName(String dMTASFClientName) {
        this.dMTASFClientName = dMTASFClientName;
    }

    public String getdMTASFSiteName() {
        return dMTASFSiteName;
    }

    public void setdMTASFSiteName(String dMTASFSiteName) {
        this.dMTASFSiteName = dMTASFSiteName;
    }

    public String getdMTASFWorkType() {
        return dMTASFWorkType;
    }

    public void setdMTASFWorkType(String dMTASFWorkType) {
        this.dMTASFWorkType = dMTASFWorkType;
    }

    public String getdMTASFProjectType() {
        return dMTASFProjectType;
    }

    public void setdMTASFProjectType(String dMTASFProjectType) {
        this.dMTASFProjectType = dMTASFProjectType;
    }

    public String getdMTASFTimeStamp() {
        return dMTASFTimeStamp;
    }

    public void setdMTASFTimeStamp(String dMTASFTimeStamp) {
        this.dMTASFTimeStamp = dMTASFTimeStamp;
    }
}
