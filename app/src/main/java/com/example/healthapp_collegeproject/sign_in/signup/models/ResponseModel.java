package com.example.healthapp_collegeproject.sign_in.signup.models;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("response")
    private String response;

    public String getResponse(){
        return response;
    }
}
