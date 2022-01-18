package com.example.myapplication.hunger_equity.model;

public class CFeedModel {
    String feedUsername;
    String feedTitle;
    String feedQuantity;
    String feedAddress;
    String feedDate;
    String feedDescription;
    String status;

    public CFeedModel() {
    }

    public CFeedModel(String feedUsername, String feedTitle, String feedQuantity, String feedAddress, String feedDate, String feedDescription, String status) {
        this.feedUsername = feedUsername;
        this.feedTitle = feedTitle;
        this.feedQuantity = feedQuantity;
        this.feedAddress = feedAddress;
        this.feedDate = feedDate;
        this.feedDescription = feedDescription;
        this.status = status;
    }

    public String getFeedUsername() {
        return feedUsername;
    }

    public void setFeedUsername(String feedUsername) {
        this.feedUsername = feedUsername;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    public String getFeedQuantity() {
        return feedQuantity;
    }

    public void setFeedQuantity(String feedQuantity) {
        this.feedQuantity = feedQuantity;
    }

    public String getFeedAddress() {
        return feedAddress;
    }

    public void setFeedAddress(String feedAddress) {
        this.feedAddress = feedAddress;
    }

    public String getFeedDate() {
        return feedDate;
    }

    public void setFeedDate(String feedDate) {
        this.feedDate = feedDate;
    }

    public String getFeedDescription() {
        return feedDescription;
    }

    public void setFeedDescription(String feedDescription) {
        this.feedDescription = feedDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CFeedModel{" +
                "feedUsername='" + feedUsername + '\'' +
                ", feedTitle='" + feedTitle + '\'' +
                ", feedQuantity='" + feedQuantity + '\'' +
                ", feedAddress='" + feedAddress + '\'' +
                ", feedDate='" + feedDate + '\'' +
                ", feedDescription='" + feedDescription + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
