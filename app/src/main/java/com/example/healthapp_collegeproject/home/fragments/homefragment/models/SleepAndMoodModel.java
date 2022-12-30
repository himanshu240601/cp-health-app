package com.example.healthapp_collegeproject.home.fragments.homefragment.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SleepAndMoodModel extends BaseObservable {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("bed_time")
    @Expose
    public String bedTime;
    @SerializedName("wake_time")
    @Expose
    public String wakeTime;
    @SerializedName("sleep_notes")
    @Expose
    public String sleepNotes;

    @SerializedName("user_mood")
    @Expose
    private String userMood;

    @SerializedName("mor_mood")
    @Expose
    private String morMood;
    @SerializedName("noon_mood")
    @Expose
    private String noonMood;
    @SerializedName("eve_mood")
    @Expose
    private String eveMood;

    @SerializedName("response")
    @Expose
    private String response;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Bindable
    public String getBedTime() {
        return bedTime;
    }

    public void setBedTime(String bedTime) {
        this.bedTime = bedTime;
        notifyChange();
    }

    @Bindable
    public String getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
        notifyChange();
    }

    @Bindable
    public String getSleepNotes() {
        return sleepNotes;
    }

    public void setSleepNotes(String sleepNotes) {
        this.sleepNotes = sleepNotes;
        notifyChange();
    }

    public String getUserMood() {
        return userMood;
    }

    public void setUserMood(String userMood) {
        this.userMood = userMood;
    }

    public String getMorMood() {
        return morMood;
    }

    public void setMorMood(String morMood) {
        this.morMood = morMood;
    }

    public String getNoonMood() {
        return noonMood;
    }

    public void setNoonMood(String noonMood) {
        this.noonMood = noonMood;
    }

    public String getEveMood() {
        return eveMood;
    }

    public void setEveMood(String eveMood) {
        this.eveMood = eveMood;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}