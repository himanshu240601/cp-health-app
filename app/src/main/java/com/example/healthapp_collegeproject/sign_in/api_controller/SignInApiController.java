package com.example.healthapp_collegeproject.sign_in.api_controller;

import com.example.healthapp_collegeproject.sign_in.login.api.LogInApiInterface;
import com.example.healthapp_collegeproject.sign_in.signup.api.SignInApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInApiController {
    private static final String URL = "http://192.168.18.10/Android/Health_App_Aiims/";

    private static SignInApiController signInApiController;
    private static Retrofit retrofit;

    SignInApiController(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized SignInApiController getInstance(){
        if(signInApiController==null){
            signInApiController = new SignInApiController();
        }
        return signInApiController;
    }

    public SignInApiInterface getApiCreateUser(){ return retrofit.create(SignInApiInterface.class); }

    public LogInApiInterface getApiLoginUser(){
        return retrofit.create(LogInApiInterface.class);
    }
}
