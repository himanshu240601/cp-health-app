package com.example.healthapp_collegeproject.sign_in.signup.api;

import com.example.healthapp_collegeproject.sign_in.signup.models.ResponseModel;
import com.example.healthapp_collegeproject.sign_in.signup.models.SignUpUserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignInApiInterface {
    @POST("db_user/db_create_user.php")
    Call<ResponseModel> createUser(@Body SignUpUserModel signUpUserModel);
}
