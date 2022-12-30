package com.example.healthapp_collegeproject.sign_in.login.api;

import com.example.healthapp_collegeproject.sign_in.login.models.LogInUserModel;
import com.example.healthapp_collegeproject.sign_in.signup.models.SignUpUserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LogInApiInterface {
    @POST("db_user/db_login_user.php")
    Call<SignUpUserModel> loginUser(@Body LogInUserModel logInUserModel);
}
