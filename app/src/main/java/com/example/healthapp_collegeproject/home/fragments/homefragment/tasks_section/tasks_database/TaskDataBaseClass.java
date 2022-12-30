package com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.tasks_database;

import android.content.Context;
import android.content.res.Configuration;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.FragmentHomeBinding;
import com.example.healthapp_collegeproject.home.fragments.homefragment.api_controller_home.DataApiController;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.PostModelFetching;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.UpdateResponse;
import com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.TasksSection;
import com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.models.TasksDataModel;
import com.example.healthapp_collegeproject.home.utility.CommonClass;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDataBaseClass {
    public static List<TasksDataModel> tasksDataModels = new ArrayList<>();

    public void fetchTasksDataFromDB(Context context, FragmentHomeBinding fragmentHomeBinding, TasksSection tasksSection) {
        PostModelFetching taskPostModel = new PostModelFetching(CommonClass.userModel.getEmail(), CommonClass.date, CommonClass.first_day);
        Call<List<TasksDataModel>> call = DataApiController.getInstance()
                .getApiTasksData()
                .fetchTasksData(taskPostModel);
        call.enqueue(new Callback<List<TasksDataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TasksDataModel>> call, @NonNull Response<List<TasksDataModel>> response) {
                if(response.body()!=null && response.isSuccessful()){
                    try {
                        System.out.println(response.body());
                        if(response.body().get(0).getResponse()==null){
                            tasksDataModels.clear();
                            tasksDataModels.addAll(response.body());

                            tasksSection.setCompletedDaysText(CommonClass.day_number-1);
                            showPieChart(fragmentHomeBinding);
                        }else{
                            Toast.makeText(context, response.body().get(0).getResponse(), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Error Please Try Again!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "An Error Occurred, Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<TasksDataModel>> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "Couldn't connect to server, Please Try Again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateTasksDataToDB(Context context, String time, String data, FragmentHomeBinding fragmentHomeBinding, TasksSection tasksSection){
        PostModelFetching taskPostModel = new PostModelFetching(CommonClass.userModel.getEmail(), CommonClass.date, time, data);
        Call<UpdateResponse> call = DataApiController.getInstance()
                .getApiTasksData()
                .updateTasksData(taskPostModel);
        call.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<UpdateResponse> call, @NonNull Response<UpdateResponse> response) {
                if(response.body()!=null && response.isSuccessful()){
                    try {
                        Toast.makeText(context, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                        fetchTasksDataFromDB(context, fragmentHomeBinding, tasksSection);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Error Please Try Again!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "An Error Occurred, Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, "Please check your internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void showPieChart(FragmentHomeBinding fragmentHomeBinding) {
        ArrayList<String> n_tasks = new ArrayList<>();
        ArrayList<String> array = new ArrayList<>();

        int day = CommonClass.day_number-1;
        if(!tasksDataModels.isEmpty() && tasksDataModels.get(day)!=null){
            if(!tasksDataModels.get(day).getWake10am().equals("")){
                n_tasks.add(tasksDataModels.get(day).getWake10am());
            }
            if(!tasksDataModels.get(day).get_10amNoon().equals("")){
                n_tasks.add(tasksDataModels.get(day).get_10amNoon());
            }
            if(!tasksDataModels.get(day).getNoon3pm().equals("")){
                n_tasks.add(tasksDataModels.get(day).getNoon3pm());
            }
            if(!tasksDataModels.get(day).get_3pm5pm().equals("")){
                n_tasks.add(tasksDataModels.get(day).get_3pm5pm());
            }
            if(!tasksDataModels.get(day).get_5pm8pm().equals("")){
                n_tasks.add(tasksDataModels.get(day).get_5pm8pm());
            }
            if(!tasksDataModels.get(day).get_8pmSleep().equals("")){
                n_tasks.add(tasksDataModels.get(day).get_8pmSleep());
            }
        }

        for(int i=0;i<n_tasks.size();i++){
            String[] b = n_tasks.get(i).split(",");
            for (String s : b) {
                array.add(s.trim());
            }
        }

        //task bar customizations
        fragmentHomeBinding.tasksTrackBar.setUsePercentValues(false);
        fragmentHomeBinding.tasksTrackBar.getDescription().setEnabled(false);
        fragmentHomeBinding.tasksTrackBar.setExtraOffsets(10, 10, 10, 10);
        fragmentHomeBinding.tasksTrackBar.setDrawHoleEnabled(true);
        fragmentHomeBinding.tasksTrackBar.setHoleRadius(90f);
        fragmentHomeBinding.tasksTrackBar.getLegend().setWordWrapEnabled(true);
        fragmentHomeBinding.tasksTrackBar.setTouchEnabled(false);

        //adding data to pie entries
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        for (String s : array) {
            if(!Objects.equals(s, "-1")){
                int val = Collections.frequency(array, s);
                if(Collections.replaceAll(array, s, "-1")){
                    pieEntries.add(new PieEntry(val, s));
                }
                else if (val != 0) {
                    pieEntries.add(new PieEntry(val, s));
                }
            }
        }

        if(pieEntries.isEmpty()){
            pieEntries.add(new PieEntry(1, ""));
        }

        //pie data set setup
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Data");
        pieDataSet.setSliceSpace(1f);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setDrawValues(false);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLinePart1Length(0.1f);
        pieDataSet.setValueLinePart2Length(0.2f);

        Context context = fragmentHomeBinding.getRoot().getContext();

        int nightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(nightMode == Configuration.UI_MODE_NIGHT_YES){
            pieDataSet.setValueLineColor(ContextCompat.getColor(context, R.color.white));
            fragmentHomeBinding.tasksTrackBar.setEntryLabelColor(ContextCompat.getColor(context, R.color.white));
            fragmentHomeBinding.tasksTrackBar.setCenterTextColor(ContextCompat.getColor(context, R.color.white));
        }else{
            pieDataSet.setValueLineColor(ContextCompat.getColor(context, R.color.black));
            fragmentHomeBinding.tasksTrackBar.setEntryLabelColor(ContextCompat.getColor(context, R.color.black));
            fragmentHomeBinding.tasksTrackBar.setCenterTextColor(ContextCompat.getColor(context, R.color.black));
        }

        fragmentHomeBinding.tasksTrackBar.setEntryLabelTextSize(9f);
        fragmentHomeBinding.tasksTrackBar.setRotationEnabled(false);
        fragmentHomeBinding.tasksTrackBar.setCenterText(CommonClass.date);
        fragmentHomeBinding.tasksTrackBar.setCenterTextSize(16f);
        fragmentHomeBinding.tasksTrackBar.setHoleColor(ContextCompat.getColor(context, R.color.transparent));

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(10f);

        fragmentHomeBinding.tasksTrackBar.setData(pieData);
        fragmentHomeBinding.tasksTrackBar.getLegend().setEnabled(false);
        fragmentHomeBinding.tasksTrackBar.animateY(1000);
    }
}
