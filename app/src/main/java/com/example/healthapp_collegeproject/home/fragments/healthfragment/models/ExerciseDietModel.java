package com.example.healthapp_collegeproject.home.fragments.healthfragment.models;

public class ExerciseDietModel {
    private int image;
    private String title;
    private String desc;

    public String getTime_reps() {
        return time_reps;
    }

    public void setTime_reps(String time_reps) {
        this.time_reps = time_reps;
    }

    private String time_reps;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ExerciseDietModel(int image, String title, String time_reps, String desc) {
        this.image = image;
        this.title = title;
        this.time_reps = time_reps;
        this.desc = desc;
    }
}
