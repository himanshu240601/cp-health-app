package com.example.healthapp_collegeproject.sign_in.signup.models;

import com.google.gson.annotations.SerializedName;

public class SignUpUserModel {
    private String user_email;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("user_phone")
    private String user_phone;

    private String user_key;
    @SerializedName("response")
    private String response;

    public SignUpUserModel(String user_key, String user_email, String user_name, String user_phone) {
        this.user_key = user_key;
        this.user_email = user_email;
        this.user_name = user_name;
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public String getResponse() {
        return response;
    }
}
