package com.coffee.coffeeapp.Model;

public class User {
    private String Name;
    private String Password;
    private String Phone;
    private String IsStaff;


    public User() {

    }

    public User(String name, String password) {
        Name=name;
        Password=password;
        IsStaff ="false";

    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void getName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void getPassword(String password) { Password = password; }


    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPhone(){
        return Phone;
    }

    public void getPhone(String phone) {
        Phone=phone;
    }
}
