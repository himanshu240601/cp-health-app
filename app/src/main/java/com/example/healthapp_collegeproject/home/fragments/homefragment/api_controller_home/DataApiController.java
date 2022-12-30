package com.example.healthapp_collegeproject.home.fragments.homefragment.api_controller_home;

import com.example.healthapp_collegeproject.home.fragments.homefragment.api_data.SleepMoodDataApiInterface;
import com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.api.TasksDataApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataApiController {

    private static final String URL = "http://192.168.18.10/Android/Health_App_Aiims/";

    private static DataApiController dataApiController;
    private static Retrofit retrofit;

    DataApiController(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized DataApiController getInstance(){
        if(dataApiController==null){
            dataApiController = new DataApiController();
        }
        return dataApiController;
    }

    public TasksDataApiInterface getApiTasksData(){ return retrofit.create(TasksDataApiInterface.class); }

    public SleepMoodDataApiInterface getApiSleepMoodData(){ return retrofit.create(SleepMoodDataApiInterface.class); }
}
