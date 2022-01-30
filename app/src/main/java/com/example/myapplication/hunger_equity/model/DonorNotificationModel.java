package com.example.myapplication.hunger_equity.model;

public class DonorNotificationModel {
    String UserName;
    String Sender;
    String Information;
    String Date;
    String Title;

    public DonorNotificationModel() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public DonorNotificationModel(String userName, String sender, String information, String date, String title) {
        UserName = userName;
        Sender = sender;
        Information = information;
        Date = date;
        Title = title;
    }

    @Override
    public String toString() {
        return "DonorNotificationModel{" +
                "UserName='" + UserName + '\'' +
                ", Sender='" + Sender + '\'' +
                ", Information='" + Information + '\'' +
                ", Date='" + Date + '\'' +
                ", Title='" + Title + '\'' +
                '}';
    }
}
