package com.example.healthapp_collegeproject.sign_in.login.models;

public class LogInUserModel {
    private String user_email;
    private String user_key;

    public LogInUserModel(String user_email, String user_key) {
        this.user_email = user_email;
        this.user_key = user_key;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }
}
