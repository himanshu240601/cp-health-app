package com.example.healthapp_collegeproject.home.fragments.homefragment.models;

public class PostModelFetching {
    private String user_email;
    private String date;
    private String first_day;

    private String time;
    private String data;

    public PostModelFetching(String user_email, String date, String time, String data) {
        this.user_email = user_email;
        this.date = date;
        this.time = time;
        this.data = data;
    }

    public PostModelFetching(String user_email, String date, String first_day) {
        this.user_email = user_email;
        this.date = date;
        this.first_day = first_day;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirst_day() {
        return first_day;
    }

    public void setFirst_day(String first_day) {
        this.first_day = first_day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
