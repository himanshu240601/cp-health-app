package com.example.healthapp_collegeproject.home.fragments.settingsfragment.profileactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.ActivityProfileBinding;
import com.example.healthapp_collegeproject.home.utility.CommonClass;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding activityProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        activityProfileBinding.setUserObject(CommonClass.userModel);

        activityProfileBinding.backBtn.setOnClickListener(view -> finish());
    }
}