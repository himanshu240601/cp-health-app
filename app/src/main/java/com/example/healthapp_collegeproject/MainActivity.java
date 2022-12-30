package com.example.healthapp_collegeproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp_collegeproject.home.HomeActivity;
import com.example.healthapp_collegeproject.get_started.GetStartedActivity;
import com.example.healthapp_collegeproject.home.fragments.homefragment.api_controller_home.DataApiController;
import com.example.healthapp_collegeproject.sign_in.api_controller.SignInApiController;

public class MainActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // access shared preferences storage
        // to check if user is logged in
        SharedPreferences sharedPreferences = getSharedPreferences("LOGGED_INFO", Context.MODE_PRIVATE);
        // store the value in isLogged variable
        boolean isLogged = sharedPreferences.getBoolean("IS_LOGGED", false);

        if(isLogged) {
            handler.postDelayed(() -> {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }, 1000);
        }
        else{
            handler.postDelayed(() -> {
                startActivity(new Intent(this, GetStartedActivity.class));
                finish();
            }, 1000);
        }
    }

    // close app and remove the callbacks and messages
    // to avoid starting activity after a handler delay
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacksAndMessages(null);
    }
}