package com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.api;

import com.example.healthapp_collegeproject.home.fragments.homefragment.models.UpdateResponse;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.PostModelFetching;
import com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.models.TasksDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TasksDataApiInterface {
    @POST("db_fetch/db_fetch_tasks_data.php")
    Call<List<TasksDataModel>> fetchTasksData(@Body PostModelFetching taskPostModel);

    @POST("db_update/db_update_task_data.php")
    Call<UpdateResponse> updateTasksData(@Body PostModelFetching taskPostModel);
}
