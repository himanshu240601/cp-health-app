package com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TasksDataModel {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("wake_10am")
    @Expose
    private String wake10am;
    @SerializedName("10am_noon")
    @Expose
    private String _10amNoon;
    @SerializedName("noon_3pm")
    @Expose
    private String noon3pm;
    @SerializedName("3pm_5pm")
    @Expose
    private String _3pm5pm;
    @SerializedName("5pm_8pm")
    @Expose
    private String _5pm8pm;
    @SerializedName("8pm_sleep")
    @Expose
    private String _8pmSleep;
    @SerializedName("response")
    @Expose
    private String response;

    public String getDate() {
        return date;
    }

    public String getWake10am() {
        return wake10am;
    }

    public String get_10amNoon() {
        return _10amNoon;
    }

    public String getNoon3pm() {
        return noon3pm;
    }

    public String get_3pm5pm() {
        return _3pm5pm;
    }

    public String get_5pm8pm() {
        return _5pm8pm;
    }

    public String get_8pmSleep() {
        return _8pmSleep;
    }

    public String getResponse() {
        return response;
    }
}
