package com.example.healthapp_collegeproject.home.fragments.homefragment.api_data;

import com.example.healthapp_collegeproject.home.fragments.homefragment.models.PostModelFetching;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.SleepAndMoodModel;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.UpdateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SleepMoodDataApiInterface {
    @POST("db_fetch/db_fetch_sleep_and_mood.php")
    Call<List<SleepAndMoodModel>> fetchSleepMoodData(@Body PostModelFetching postModelFetching);

    @POST("db_update/db_update_mood_data.php")
    Call<UpdateResponse> updateMoodData(@Body PostModelFetching postModelFetching);

    @POST("db_update/db_update_mood_rating.php")
    Call<UpdateResponse> updateMoodRating(@Body PostModelFetching postModelFetching);

    @POST("db_update/db_update_sleep_data.php")
    Call<UpdateResponse> updateSleepData(@Body PostModelFetching postModelFetching);
}
