package com.example.healthapp_collegeproject.home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.ActivityHomeBinding;
import com.example.healthapp_collegeproject.home.fragments.healthfragment.HealthFragment;
import com.example.healthapp_collegeproject.home.fragments.homefragment.HomeFragment;
import com.example.healthapp_collegeproject.home.fragments.settingsfragment.SettingsFragment;
import com.example.healthapp_collegeproject.home.utility.CommonClass;

public class HomeActivity extends AppCompatActivity {
    //initializing the fragment variables
    //so that we can replace the fragments
    //instead of re creating them
    //each time changing the screen
    private final HomeFragment homeFragment = new HomeFragment();
    private final HealthFragment healthFragment = new HealthFragment();
    private final SettingsFragment settingsFragment = new SettingsFragment();
    //set the default fragment as home fragment
    public Fragment selected = homeFragment;

    private final CommonClass commonClass = new CommonClass(this);

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        commonClass.setUserModel();
        commonClass.setDate();

        createFragment(homeFragment);
        createFragment(healthFragment);
        createFragment(settingsFragment);

        changeFragment(homeFragment);

        activityHomeBinding.bottomNavigationBar.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.page_1:
                    changeFragment(homeFragment);
                    selected = homeFragment;
                    return true;
                case R.id.page_2:
                    changeFragment(healthFragment);
                    selected = healthFragment;
                    return true;
                case R.id.page_4:
                    changeFragment(settingsFragment);
                    selected = settingsFragment;
                    return true;
            }
            return false;
        });
    }

    //function to create fragments
    private void createFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentsContent, fragment)
                .hide(fragment)
                .commit();
    }

    //change the fragments
    private void changeFragment(Fragment fragment){
        //first hide the already active fragment
        getSupportFragmentManager().beginTransaction()
                .hide(selected)
                .commit();
        //then show the fragment according to the
        //current bottom selected item
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}