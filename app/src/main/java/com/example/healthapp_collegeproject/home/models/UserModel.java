package com.example.healthapp_collegeproject.home.models;

public class UserModel {
    private String name;
    private String email;
    private String phone;
    private String first_name;

    public UserModel(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        setFirst_name(name.split(" "));
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String[] first_name) {
        this.first_name = first_name[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
