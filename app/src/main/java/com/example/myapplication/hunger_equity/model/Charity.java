package com.example.myapplication.hunger_equity.model;

public class Charity {
    String C_Address;
    String C_Email;
    String C_Name;
    String C_Organ;
    String C_Password;
    String C_Phone;

    public Charity(String c_Address, String c_Email, String c_Name, String c_Organ, String c_Password, String c_Phone) {
        C_Address = c_Address;
        C_Email = c_Email;
        C_Name = c_Name;
        C_Organ = c_Organ;
        C_Password = c_Password;
        C_Phone = c_Phone;
    }

    public Charity() {
    }

    public Charity(Class<Charity> charityClass) {
    }

    public String getC_Address() {
        return C_Address;
    }

    public void setC_Address(String c_Address) {
        C_Address = c_Address;
    }

    public String getC_Email() {
        return C_Email;
    }

    public void setC_Email(String c_Email) {
        C_Email = c_Email;
    }

    public String getC_Name() {
        return C_Name;
    }

    public void setC_Name(String c_Name) {
        C_Name = c_Name;
    }

    public String getC_Organ() {
        return C_Organ;
    }

    public void setC_Organ(String c_Organ) {
        C_Organ = c_Organ;
    }

    public String getC_Password() {
        return C_Password;
    }

    public void setC_Password(String c_Password) {
        C_Password = c_Password;
    }

    public String getC_Phone() {
        return C_Phone;
    }

    public void setC_Phone(String c_Phone) {
        C_Phone = c_Phone;
    }

    @Override
    public String toString() {
        return "Charity{" +
                "C_Address='" + C_Address + '\'' +
                ", C_Email='" + C_Email + '\'' +
                ", C_Name='" + C_Name + '\'' +
                ", C_Organ='" + C_Organ + '\'' +
                ", C_Password='" + C_Password + '\'' +
                ", C_Phone='" + C_Phone + '\'' +
                '}';
    }
}


