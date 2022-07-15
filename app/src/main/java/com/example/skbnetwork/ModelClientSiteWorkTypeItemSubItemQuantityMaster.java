package com.example.skbnetwork;

public class ModelClientSiteWorkTypeItemSubItemQuantityMaster {

    String dMCSWTISIQMClientName;
    String dMCSWTISIQMSiteName;
    String dMCSWTISIQMWorkType;
    String dMCSWTISIQMItemCategory;
    String dMCSWTISIQMSubItem;
    String dMCSWTISIQMSearchKey1;
    String dMCSWTISIQMSearchKey2;
    int currentDemand;
    int approvedDemand;
    int totalDemand;
    int totalApproved;
    int currentPurchased;
    int totalPurchased;
    int currentReceived;
    int totalReceived;
    int lastBilled;
    int totalBilled;
    int workInProgress;
    int stokeInHand;

    public ModelClientSiteWorkTypeItemSubItemQuantityMaster() {
    }

    public ModelClientSiteWorkTypeItemSubItemQuantityMaster(String dMCSWTISIQMClientName, String dMCSWTISIQMSiteName,
                                                            String dMCSWTISIQMWorkType, String dMCSWTISIQMItemCategory,
                                                            String dMCSWTISIQMSubItem, String dMCSWTISIQMSearchKey1,
                                                            String dMCSWTISIQMSearchKey2, int currentDemand, int approvedDemand,
                                                            int totalDemand, int totalApproved, int currentPurchased,
                                                            int totalPurchased, int currentReceived,
                                                            int totalReceived, int lastBilled, int totalBilled,
                                                            int workInProgress, int stokeInHand) {
        this.dMCSWTISIQMClientName = dMCSWTISIQMClientName;
        this.dMCSWTISIQMSiteName = dMCSWTISIQMSiteName;
        this.dMCSWTISIQMWorkType = dMCSWTISIQMWorkType;
        this.dMCSWTISIQMItemCategory = dMCSWTISIQMItemCategory;
        this.dMCSWTISIQMSubItem = dMCSWTISIQMSubItem;
        this.dMCSWTISIQMSearchKey1 = dMCSWTISIQMSearchKey1;
        this.dMCSWTISIQMSearchKey2 = dMCSWTISIQMSearchKey2;
        this.currentDemand = currentDemand;
        this.approvedDemand = approvedDemand;
        this.totalDemand = totalDemand;
        this.totalApproved = totalApproved;
        this.currentPurchased = currentPurchased;
        this.totalPurchased = totalPurchased;
        this.currentReceived = currentReceived;
        this.totalReceived = totalReceived;
        this.lastBilled = lastBilled;
        this.totalBilled = totalBilled;
        this.workInProgress = workInProgress;
        this.stokeInHand = stokeInHand;
    }

    @Override
    public String toString() {
        return "ModelClientSiteWorkTypeItemSubItemQuantityMaster{" +
                "dMCSWTISIQMClientName='" + dMCSWTISIQMClientName + '\'' +
                ", dMCSWTISIQMSiteName='" + dMCSWTISIQMSiteName + '\'' +
                ", dMCSWTISIQMWorkType='" + dMCSWTISIQMWorkType + '\'' +
                ", dMCSWTISIQMItemCategory='" + dMCSWTISIQMItemCategory + '\'' +
                ", dMCSWTISIQMSubItem='" + dMCSWTISIQMSubItem + '\'' +
                ", dMCSWTISIQMSearchKey1='" + dMCSWTISIQMSearchKey1 + '\'' +
                ", dMCSWTISIQMSearchKey2='" + dMCSWTISIQMSearchKey2 + '\'' +
                ", currentDemand=" + currentDemand +
                ", approvedDemand=" + approvedDemand +
                ", totalDemand=" + totalDemand +
                ", totalApproved=" + totalApproved +
                ", currentPurchased=" + currentPurchased +
                ", totalPurchased=" + totalPurchased +
                ", currentReceived=" + currentReceived +
                ", totalReceived=" + totalReceived +
                ", lastBilled=" + lastBilled +
                ", totalBilled=" + totalBilled +
                ", workInProgress=" + workInProgress +
                ", stokeInHand=" + stokeInHand +
                '}';
    }

    public String getdMCSWTISIQMClientName() {
        return dMCSWTISIQMClientName;
    }

    public void setdMCSWTISIQMClientName(String dMCSWTISIQMClientName) {
        this.dMCSWTISIQMClientName = dMCSWTISIQMClientName;
    }

    public String getdMCSWTISIQMSiteName() {
        return dMCSWTISIQMSiteName;
    }

    public void setdMCSWTISIQMSiteName(String dMCSWTISIQMSiteName) {
        this.dMCSWTISIQMSiteName = dMCSWTISIQMSiteName;
    }

    public String getdMCSWTISIQMWorkType() {
        return dMCSWTISIQMWorkType;
    }

    public void setdMCSWTISIQMWorkType(String dMCSWTISIQMWorkType) {
        this.dMCSWTISIQMWorkType = dMCSWTISIQMWorkType;
    }

    public String getdMCSWTISIQMItemCategory() {
        return dMCSWTISIQMItemCategory;
    }

    public void setdMCSWTISIQMItemCategory(String dMCSWTISIQMItemCategory) {
        this.dMCSWTISIQMItemCategory = dMCSWTISIQMItemCategory;
    }

    public String getdMCSWTISIQMSubItem() {
        return dMCSWTISIQMSubItem;
    }

    public void setdMCSWTISIQMSubItem(String dMCSWTISIQMSubItem) {
        this.dMCSWTISIQMSubItem = dMCSWTISIQMSubItem;
    }

    public String getdMCSWTISIQMSearchKey1() {
        return dMCSWTISIQMSearchKey1;
    }

    public void setdMCSWTISIQMSearchKey1(String dMCSWTISIQMSearchKey1) {
        this.dMCSWTISIQMSearchKey1 = dMCSWTISIQMSearchKey1;
    }

    public String getdMCSWTISIQMSearchKey2() {
        return dMCSWTISIQMSearchKey2;
    }

    public void setdMCSWTISIQMSearchKey2(String dMCSWTISIQMSearchKey2) {
        this.dMCSWTISIQMSearchKey2 = dMCSWTISIQMSearchKey2;
    }

    public int getCurrentDemand() {
        return currentDemand;
    }

    public void setCurrentDemand(int currentDemand) {
        this.currentDemand = currentDemand;
    }

    public int getApprovedDemand() {
        return approvedDemand;
    }

    public void setApprovedDemand(int approvedDemand) {
        this.approvedDemand = approvedDemand;
    }

    public int getTotalDemand() {
        return totalDemand;
    }

    public void setTotalDemand(int totalDemand) {
        this.totalDemand = totalDemand;
    }

    public int getTotalApproved() {
        return totalApproved;
    }

    public void setTotalApproved(int totalApproved) {
        this.totalApproved = totalApproved;
    }

    public int getCurrentPurchased() {
        return currentPurchased;
    }

    public void setCurrentPurchased(int currentPurchased) {
        this.currentPurchased = currentPurchased;
    }

    public int getTotalPurchased() {
        return totalPurchased;
    }

    public void setTotalPurchased(int totalPurchased) {
        this.totalPurchased = totalPurchased;
    }

    public int getCurrentReceived() {
        return currentReceived;
    }

    public void setCurrentReceived(int currentReceived) {
        this.currentReceived = currentReceived;
    }

    public int getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(int totalReceived) {
        this.totalReceived = totalReceived;
    }

    public int getLastBilled() {
        return lastBilled;
    }

    public void setLastBilled(int lastBilled) {
        this.lastBilled = lastBilled;
    }

    public int getTotalBilled() {
        return totalBilled;
    }

    public void setTotalBilled(int totalBilled) {
        this.totalBilled = totalBilled;
    }

    public int getWorkInProgress() {
        return workInProgress;
    }

    public void setWorkInProgress(int workInProgress) {
        this.workInProgress = workInProgress;
    }

    public int getStokeInHand() {
        return stokeInHand;
    }

    public void setStokeInHand(int stokeInHand) {
        this.stokeInHand = stokeInHand;
    }
}
