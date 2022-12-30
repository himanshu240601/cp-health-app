package com.example.healthapp_collegeproject.home.fragments.homefragment.mood_section;

import android.content.Context;
import android.content.res.Configuration;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.FragmentHomeBinding;
import com.example.healthapp_collegeproject.home.fragments.homefragment.HomeFragment;
import com.example.healthapp_collegeproject.home.fragments.homefragment.api_controller_home.DataApiController;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.PostModelFetching;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.SleepAndMoodModel;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.UpdateResponse;
import com.example.healthapp_collegeproject.home.utility.CommonClass;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoodSection {
    private final Context context;
    private final FragmentHomeBinding fragmentHomeBinding;

    public MoodSection(Context context, FragmentHomeBinding fragmentHomeBinding){
        this.context = context;
        this.fragmentHomeBinding = fragmentHomeBinding;
    }

    public void updateMoodDataToDB(String data){
        PostModelFetching taskPostModel = new PostModelFetching(CommonClass.userModel.getEmail(), CommonClass.date, data);
        Call<UpdateResponse> call = DataApiController.getInstance()
                .getApiSleepMoodData()
                .updateMoodData(taskPostModel);
        call.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<UpdateResponse> call, @NonNull Response<UpdateResponse> response) {
                if(response.body()!=null && response.isSuccessful()){
                    try {
                        Toast.makeText(context, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                        new HomeFragment().fetchFromDBSleepMood(context, fragmentHomeBinding);
                    } catch (Exception e) {
                        Toast.makeText(context, "Error Please Try Again!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "An Error Occurred, Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "Please check your internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void highlightFunction(String m, boolean isFetched) {
        removeHighlighting();
        switch (m) {
            case "ANX":
                fragmentHomeBinding.anxious.setAlpha(1);
                break;
            case "SAD":
                fragmentHomeBinding.sad.setAlpha(1);
                break;
            case "HAP":
                fragmentHomeBinding.happy.setAlpha(1);
                break;
            case "FEA":
                fragmentHomeBinding.fear.setAlpha(1);
                break;
            case "ANG":
                fragmentHomeBinding.anger.setAlpha(1);
                break;
            default:
                break;
        }
        if(!isFetched){
            updateMoodDataToDB(m);
        }
    }

    private void removeHighlighting() {
        fragmentHomeBinding.anxious.setAlpha(0.5f);
        fragmentHomeBinding.sad.setAlpha(0.5f);
        fragmentHomeBinding.happy.setAlpha(0.5f);
        fragmentHomeBinding.fear.setAlpha(0.5f);
        fragmentHomeBinding.anger.setAlpha(0.5f);
    }

    public void showMoodChart(){
        List<SleepAndMoodModel> sleepAndMoodModel = HomeFragment.sleepAndMoodModel;

        fragmentHomeBinding.moodTrackBar.setScaleEnabled(false); //disable zoom-in

        fragmentHomeBinding.moodTrackBar.getAxisLeft().setValueFormatter(new IndexAxisValueFormatter(CommonClass.getMoodImages()));
        fragmentHomeBinding.moodTrackBar.getAxisLeft().setAxisMinimum(0); //setting min value across y-axis
        fragmentHomeBinding.moodTrackBar.getAxisLeft().setAxisMaximum(6); //setting max value across y-axis
        fragmentHomeBinding.moodTrackBar.getAxisLeft().setDrawGridLines(false);

        fragmentHomeBinding.moodTrackBar.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        fragmentHomeBinding.moodTrackBar.getXAxis().setDrawGridLines(false);
        fragmentHomeBinding.moodTrackBar.getAxisRight().setEnabled(false);
        fragmentHomeBinding.moodTrackBar.getDescription().setEnabled(false);
        fragmentHomeBinding.moodTrackBar.animateY(500);

        XAxis xAxis = fragmentHomeBinding.moodTrackBar.getXAxis();
        //set week days on bar chart's x axis
        xAxis.setValueFormatter(new IndexAxisValueFormatter(CommonClass.getWeekDays()));
        xAxis.setLabelCount(8);
        xAxis.setAxisMaximum(8);
        xAxis.setAxisMinimum(0);
        int nightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(nightMode == Configuration.UI_MODE_NIGHT_YES){
            xAxis.setTextColor(ContextCompat.getColor(context, R.color.white));
        }else{
            xAxis.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

        ArrayList<Entry> data = new ArrayList<>();
        for(int i=0;i<CommonClass.day_number;i++) {
            String val = "";
            try{
                val = sleepAndMoodModel.get(i).getUserMood();
            }catch (Exception e){
                e.printStackTrace();
            }
            switch (val){
                case "HAP":
                    data.add(new Entry(i+1, 5));
                    break;
                case "ANX":
                    data.add(new Entry(i+1, 1));
                    break;
                case "SAD":
                    data.add(new Entry(i+1, 2));
                    break;
                case "FEA":
                    data.add(new Entry(i+1, 3));
                    break;
                case "ANG":
                    data.add(new Entry(i+1, 4));
                    break;
                default:
                    break;
            }
        }

        //initializing dataset for fragmentHomeBinding.moodTrackBar
        LineDataSet linedataset = new LineDataSet(data, "Mood Data");
        linedataset.setValueTextSize(14f);
        linedataset.setColor(ContextCompat.getColor(context, R.color.blue));
        linedataset.setCircleColor(ContextCompat.getColor(context, R.color.blue));
        linedataset.setDrawValues(false);

        //initialize bar data
        LineData lineData = new LineData(linedataset);
        fragmentHomeBinding.moodTrackBar.getLegend().setEnabled(false);
        fragmentHomeBinding.moodTrackBar.setData(lineData);
    }
}
