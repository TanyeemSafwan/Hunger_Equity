package com.example.myapplication.hunger_equity.model;

public class DFeedModel {
    String feedUsername;
    String feedTitle;
    String feedQuantity;
    String feedPlace;
    String feedTime;
    String feedDate;
    String status;

    public DFeedModel() {
    }

    public DFeedModel(String feedUsername, String feedTitle, String feedQuantity, String feedPlace, String feedTime, String feedDate, String status) {
        this.feedUsername = feedUsername;
        this.feedTitle = feedTitle;
        this.feedQuantity = feedQuantity;
        this.feedPlace = feedPlace;
        this.feedTime = feedTime;
        this.feedDate = feedDate;
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

    public String getFeedPlace() {
        return feedPlace;
    }

    public void setFeedPlace(String feedPlace) {
        this.feedPlace = feedPlace;
    }

    public String getFeedTime() {
        return feedTime;
    }

    public void setFeedTime(String feedTime) {
        this.feedTime = feedTime;
    }

    public String getFeedDate() {
        return feedDate;
    }

    public void setFeedDate(String feedDate) {
        this.feedDate = feedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DFeedModel{" +
                "feedUsername='" + feedUsername + '\'' +
                ", feedTitle='" + feedTitle + '\'' +
                ", feedQuantity='" + feedQuantity + '\'' +
                ", feedPlace='" + feedPlace + '\'' +
                ", feedTime='" + feedTime + '\'' +
                ", feedDate='" + feedDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
