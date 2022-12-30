package com.example.healthapp_collegeproject.home.fragments.homefragment.sleep_section;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.CustomDialogBoxBinding;
import com.example.healthapp_collegeproject.databinding.FragmentHomeBinding;
import com.example.healthapp_collegeproject.home.fragments.homefragment.HomeFragment;
import com.example.healthapp_collegeproject.home.fragments.homefragment.api_controller_home.DataApiController;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.PostModelFetching;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.SleepAndMoodModel;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.UpdateResponse;
import com.example.healthapp_collegeproject.home.utility.CommonClass;
import com.example.healthapp_collegeproject.home.utility.MaterialTimePickerDialog;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SleepSection {
    private final Context context;
    private final FragmentHomeBinding fragmentHomeBinding;

    public SleepSection(Context context, FragmentHomeBinding fragmentHomeBinding){
        this.context = context;
        this.fragmentHomeBinding = fragmentHomeBinding;
    }

    public void updateSleepDataToDB(String data, String type){
        PostModelFetching taskPostModel = new PostModelFetching(CommonClass.userModel.getEmail(), CommonClass.date, type, data);
        Call<UpdateResponse> call = (type.equals("morning") || type.equals("noon") || type.equals("evening"))
                ? DataApiController.getInstance()
                .getApiSleepMoodData()
                .updateMoodRating(taskPostModel)
                : DataApiController.getInstance()
                .getApiSleepMoodData()
                .updateSleepData(taskPostModel);
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

    public void setTime(boolean id, FragmentManager fragmentManager){
            if (id){
                new MaterialTimePickerDialog().getTimeFromDialog("Select wakeup time", fragmentManager, fragmentHomeBinding.getDataObject(), true, this);

            }else{
                new MaterialTimePickerDialog().getTimeFromDialog("Select bed time", fragmentManager, fragmentHomeBinding.getDataObject(), false, this);
            }
    }

    public void openSleepNotesDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup viewGroup = fragmentHomeBinding.getRoot().findViewById(android.R.id.content);
        CustomDialogBoxBinding customDialogBoxBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_dialog_box, viewGroup, false);

        builder.setView(customDialogBoxBinding.getRoot());
        AlertDialog alertDialog = builder.create();

        customDialogBoxBinding.textInputDialog.setHint(R.string.add_your_sleep_notes);

        if(!fragmentHomeBinding.getDataObject().sleepNotes.isEmpty()){
            customDialogBoxBinding.editTextDialog.setText(fragmentHomeBinding.getDataObject().sleepNotes);
        }

        customDialogBoxBinding.saveButton.setOnClickListener(view -> {
            String value = Objects.requireNonNull(customDialogBoxBinding.editTextDialog.getText()).toString();
            if(!value.isEmpty()){
                fragmentHomeBinding.getDataObject().setSleepNotes(value);
                updateSleepDataToDB(value,"notes");
                alertDialog.dismiss();
            }else{
                Toast.makeText(context,context.getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
            }
        });
        customDialogBoxBinding.cancelButton.setOnClickListener(view12 -> alertDialog.dismiss());
        alertDialog.show();
    }

    public void showSleepBarChart(){
        List<SleepAndMoodModel> sleepAndMoodModel = new ArrayList<>(HomeFragment.sleepAndMoodModel);

        String [] sleep_hours_bt = new String[CommonClass.day_number];
        String [] sleep_hours_wt = new String[CommonClass.day_number];

        for(int i=0;i<CommonClass.day_number;i++){
            sleep_hours_bt[i] = sleepAndMoodModel.get(i).getBedTime().isEmpty() ? "00:00 AM" : sleepAndMoodModel.get(i).getBedTime();
            sleep_hours_wt[i] = sleepAndMoodModel.get(i).getWakeTime().isEmpty() ? "00:00 AM" : sleepAndMoodModel.get(i).getWakeTime();
        }

        double [] total_sleep_hours = new double[CommonClass.day_number];
        for(int i=0;i<CommonClass.day_number;i++){
            try{
                total_sleep_hours[i] = CommonClass.findTimeDifference(sleep_hours_wt[i], sleep_hours_bt[i]);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        ArrayList<BarEntry> data = new ArrayList<>();
        data.add(new BarEntry(0, 0));
        for(int i=1;i<=CommonClass.day_number;i++) {
            data.add(new BarEntry(i, (float) total_sleep_hours[i - 1]));
        }

        fragmentHomeBinding.sleepTrackGraph.setScaleEnabled(false); //disable zoom-in
        fragmentHomeBinding.sleepTrackGraph.getAxisLeft().setValueFormatter(new IndexAxisValueFormatter(CommonClass.getTotalHours()));
        fragmentHomeBinding.sleepTrackGraph.getAxisLeft().setAxisMinimum(0); //setting min value across y-axis
        fragmentHomeBinding.sleepTrackGraph.getAxisLeft().setAxisMaximum(13); //setting max value across y-axis

        fragmentHomeBinding.sleepTrackGraph.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        fragmentHomeBinding.sleepTrackGraph.getXAxis().setDrawGridLines(false);
        fragmentHomeBinding.sleepTrackGraph.getAxisRight().setEnabled(false);
        fragmentHomeBinding.sleepTrackGraph.getDescription().setEnabled(false);
        fragmentHomeBinding.sleepTrackGraph.animateY(500);

        int nightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(nightMode == Configuration.UI_MODE_NIGHT_YES){
            fragmentHomeBinding.sleepTrackGraph.getXAxis().setTextColor(ContextCompat.getColor(context, R.color.white));
            fragmentHomeBinding.sleepTrackGraph.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        }else{
            fragmentHomeBinding.sleepTrackGraph.getXAxis().setTextColor(ContextCompat.getColor(context, R.color.black));
            fragmentHomeBinding.sleepTrackGraph.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.black));
        }

        XAxis xAxis = fragmentHomeBinding.sleepTrackGraph.getXAxis();
        //set week days on bar chart's x axis
        xAxis.setValueFormatter(new IndexAxisValueFormatter(CommonClass.getWeekDays()));
        xAxis.setLabelCount(8);
        xAxis.setAxisMaximum(8);
        xAxis.setAxisMinimum(0);

        BarDataSet barDataSet = new BarDataSet(data, "sleep data");
        barDataSet.setValueTextSize(14f);
        barDataSet.setColor(ContextCompat.getColor(context, R.color.blue));
        barDataSet.setDrawValues(false);

        BarData barData = new BarData(barDataSet);
        fragmentHomeBinding.sleepTrackGraph.setData(barData);
        fragmentHomeBinding.sleepTrackGraph.getLegend().setEnabled(false);
        fragmentHomeBinding.sleepTrackGraph.invalidate();
    }
}
