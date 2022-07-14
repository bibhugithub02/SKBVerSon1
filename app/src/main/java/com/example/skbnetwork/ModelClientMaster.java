package com.example.skbnetwork;

public class ModelClientMaster {

    String dMCMClientName;
    String dMCMCSiteName;
    String dMCMCProjectType;
    String dMCMCWorkType;
    String dMCMCDateStamp;
    String dMCMCFiller01;
    String dMCMCFiller02;

    public ModelClientMaster() {
    }

    public ModelClientMaster(String dMCMClientName, String dMCMCSiteName,
                             String dMCMCProjectType, String dMCMCWorkType,
                             String dMCMCDateStamp, String dMCMCFiller01, String dMCMCFiller02) {
        this.dMCMClientName = dMCMClientName;
        this.dMCMCSiteName = dMCMCSiteName;
        this.dMCMCProjectType = dMCMCProjectType;
        this.dMCMCWorkType = dMCMCWorkType;
        this.dMCMCDateStamp = dMCMCDateStamp;
        this.dMCMCFiller01 = dMCMCFiller01;
        this.dMCMCFiller02 = dMCMCFiller02;
    }

    @Override
    public String toString() {
        return "ModelClientMaster{" +
                "dMCMClientName='" + dMCMClientName + '\'' +
                ", dMCMCSiteName='" + dMCMCSiteName + '\'' +
                ", dMCMCProjectType='" + dMCMCProjectType + '\'' +
                ", dMCMCWorkType='" + dMCMCWorkType + '\'' +
                ", dMCMCDateStamp='" + dMCMCDateStamp + '\'' +
                ", dMCMCFiller01='" + dMCMCFiller01 + '\'' +
                ", dMCMCFiller02='" + dMCMCFiller02 + '\'' +
                '}';
    }

    public String getdMCMClientName() {
        return dMCMClientName;
    }

    public void setdMCMClientName(String dMCMClientName) {
        this.dMCMClientName = dMCMClientName;
    }

    public String getdMCMCSiteName() {
        return dMCMCSiteName;
    }

    public void setdMCMCSiteName(String dMCMCSiteName) {
        this.dMCMCSiteName = dMCMCSiteName;
    }

    public String getdMCMCProjectType() {
        return dMCMCProjectType;
    }

    public void setdMCMCProjectType(String dMCMCProjectType) {
        this.dMCMCProjectType = dMCMCProjectType;
    }

    public String getdMCMCWorkType() {
        return dMCMCWorkType;
    }

    public void setdMCMCWorkType(String dMCMCWorkType) {
        this.dMCMCWorkType = dMCMCWorkType;
    }

    public String getdMCMCDateStamp() {
        return dMCMCDateStamp;
    }

    public void setdMCMCDateStamp(String dMCMCDateStamp) {
        this.dMCMCDateStamp = dMCMCDateStamp;
    }

    public String getdMCMCFiller01() {
        return dMCMCFiller01;
    }

    public void setdMCMCFiller01(String dMCMCFiller01) {
        this.dMCMCFiller01 = dMCMCFiller01;
    }

    public String getdMCMCFiller02() {
        return dMCMCFiller02;
    }

    public void setdMCMCFiller02(String dMCMCFiller02) {
        this.dMCMCFiller02 = dMCMCFiller02;
    }
}
