package com.example.healthapp_collegeproject.get_started;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.ActivityGetStartedBinding;
import com.example.healthapp_collegeproject.get_started.models.SliderItemsModel;
import com.example.healthapp_collegeproject.sign_in.SignInActivity;

import java.util.ArrayList;
import java.util.List;

public class GetStartedActivity extends AppCompatActivity {

    private final List<SliderItemsModel> sliderItemsList = new ArrayList<>();

    private ActivityGetStartedBinding activityGetStartedBinding;

    private final int[] status_bar_bg = {
            Color.parseColor("#98e8f1"),
            Color.parseColor("#ebd8d0"),
            Color.parseColor("#6ee8ff"),
    };

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGetStartedBinding = DataBindingUtil.setContentView(this, R.layout.activity_get_started);

        position = 0;

        initializeSliderItems();

        setState();
        
        activityGetStartedBinding.nextBtn.setOnClickListener(view->{
            position++;
            if(position>0){
                activityGetStartedBinding.previousBtn.setVisibility(View.VISIBLE);
            }
            if(position==3){
                startSingInActivity();
            }else{
                setState();
            }
        });

        activityGetStartedBinding.previousBtn.setOnClickListener(view->{
            position--;
            if(position==0){
                activityGetStartedBinding.previousBtn.setVisibility(View.GONE);
            }
            setState();
        });

        activityGetStartedBinding.skipGetStarted.setOnClickListener(view-> startSingInActivity());
    }

    private void startSingInActivity(){
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    private void setState() {
        getWindow().setStatusBarColor(status_bar_bg[position]);
        activityGetStartedBinding.setSliderObject(sliderItemsList.get(position));
    }

    private void initializeSliderItems(){
        sliderItemsList.add(
                new SliderItemsModel(
                        ContextCompat.getDrawable(this, R.drawable.get_started_1),
                        R.string.track_your_daily_tasks_and_activities,
                        R.string.with_our_activity_calendar_analyze_your_tasks_and_activities, 1, R.string.next)
        );
        sliderItemsList.add(
                new SliderItemsModel(
                        ContextCompat.getDrawable(this, R.drawable.get_started_2),
                        R.string.check_how_was_your_mood_during_day,
                        R.string.analyze_your_emotions_and_know_your_mood_variations_, 2, R.string.next)
        );
        sliderItemsList.add(
                new SliderItemsModel(
                        ContextCompat.getDrawable(this, R.drawable.get_started_3),
                        R.string.know_if_your_are_getting_enough_sleep,
                        R.string.monitor_your_sleep_hours_by_analyzing_your_sleep_and_wake_time, 3, R.string.continue_to_app)
        );
    }
}