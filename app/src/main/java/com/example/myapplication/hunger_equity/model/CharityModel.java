package com.example.myapplication.hunger_equity.model;

public class CharityModel {
    private String imageUrl;
    public CharityModel()
    {

    }
    public CharityModel(String imageUrl)
    {
        this.imageUrl=imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "CharityModel{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
