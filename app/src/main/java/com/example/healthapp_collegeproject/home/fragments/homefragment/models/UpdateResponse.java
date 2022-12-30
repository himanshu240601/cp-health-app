package com.example.healthapp_collegeproject.home.fragments.homefragment.models;

import com.google.gson.annotations.SerializedName;

public class UpdateResponse {
    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }
}
