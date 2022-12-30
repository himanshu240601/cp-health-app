package com.example.healthapp_collegeproject.home.fragments.settingsfragment.aboutactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {

    ActivityAboutBinding activityAboutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutBinding = DataBindingUtil.setContentView(this, R.layout.activity_about);

        activityAboutBinding.backBtn.setOnClickListener(view -> finish());
    }
}