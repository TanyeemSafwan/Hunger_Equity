package com.example.myapplication.hunger_equity;

public class Donor {
    String D_Address;
    String D_Email;
    String D_Name;
    String D_Password;
    String D_Phone;

    public Donor() {
    }

    public Donor(String d_Address, String d_Email, String d_Name, String d_Password, String d_Phone) {
        D_Address = d_Address;
        D_Email = d_Email;
        D_Name = d_Name;
        D_Password = d_Password;
        D_Phone = d_Phone;
    }

    public String getD_Address() {
        return D_Address;
    }

    public void setD_Address(String d_Address) {
        D_Address = d_Address;
    }

    public String getD_Email() {
        return D_Email;
    }

    public void setD_Email(String d_Email) {
        D_Email = d_Email;
    }

    public String getD_Name() {
        return D_Name;
    }

    public void setD_Name(String d_Name) {
        D_Name = d_Name;
    }

    public String getD_Password() {
        return D_Password;
    }

    public void setD_Password(String d_Password) {
        D_Password = d_Password;
    }

    public String getD_Phone() {
        return D_Phone;
    }

    public void setD_Phone(String d_Phone) {
        D_Phone = d_Phone;
    }
}
