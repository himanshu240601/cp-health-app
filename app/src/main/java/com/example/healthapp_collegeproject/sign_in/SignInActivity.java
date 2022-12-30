package com.example.healthapp_collegeproject.sign_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.ActivitySignInBinding;
import com.example.healthapp_collegeproject.sign_in.login.LoginActivity;
import com.example.healthapp_collegeproject.sign_in.signup.SignupActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignInBinding activitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

        getWindow().setStatusBarColor(Color.parseColor("#999dd9"));

        activitySignInBinding.buttonLogIn.setOnClickListener(view-> startActivity(new Intent(this, LoginActivity.class)));

        activitySignInBinding.buttonSignUp.setOnClickListener(view-> startActivity(new Intent(this, SignupActivity.class)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}