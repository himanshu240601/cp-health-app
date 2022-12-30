package com.example.healthapp_collegeproject.home.fragments.homefragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.FragmentHomeBinding;
import com.example.healthapp_collegeproject.home.fragments.homefragment.api_controller_home.DataApiController;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.PostModelFetching;
import com.example.healthapp_collegeproject.home.fragments.homefragment.models.SleepAndMoodModel;
import com.example.healthapp_collegeproject.home.fragments.homefragment.mood_section.MoodSection;
import com.example.healthapp_collegeproject.home.fragments.homefragment.sleep_section.SleepSection;
import com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.TasksSection;
import com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.tasks_database.TaskDataBaseClass;
import com.example.healthapp_collegeproject.home.fragments.settingsfragment.profileactivity.ProfileActivity;
import com.example.healthapp_collegeproject.home.utility.CheckInternetConnection;
import com.example.healthapp_collegeproject.home.utility.CommonClass;
import com.google.android.material.slider.Slider;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{

    private FragmentHomeBinding fragmentHomeBinding;

    private final TaskDataBaseClass taskDataBaseClass = new TaskDataBaseClass();

    @SuppressLint("StaticFieldLeak")
    private static MoodSection moodSection;
    @SuppressLint("StaticFieldLeak")
    private static SleepSection sleepSection;

    private String mood_rating_type;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        View view = fragmentHomeBinding.getRoot();

        fragmentHomeBinding.setUserObject(CommonClass.userModel);

        moodSection = new MoodSection(view.getContext(), fragmentHomeBinding);
        sleepSection = new SleepSection(view.getContext(), fragmentHomeBinding);
        TasksSection tasksSection = new TasksSection(view.getContext(), fragmentHomeBinding, view);

        tasksSection.setTableRows();

        mood_rating_type = CommonClass.checkDayStatus(fragmentHomeBinding);

        new CheckInternetConnection(fragmentHomeBinding, tasksSection, taskDataBaseClass).listenForInternetConnectivity(view.getContext());

        taskDataBaseClass.fetchTasksDataFromDB(view.getContext(), fragmentHomeBinding, tasksSection);
        fetchFromDBSleepMood(view.getContext(), fragmentHomeBinding);

        fragmentHomeBinding.openProfile.setOnClickListener(view1-> startActivity(new Intent(requireContext(), ProfileActivity.class)));

        fragmentHomeBinding.anxious.setOnClickListener(view1 -> moodSection.highlightFunction("ANX", false));
        fragmentHomeBinding.sad.setOnClickListener(view1 -> moodSection.highlightFunction("SAD", false));
        fragmentHomeBinding.happy.setOnClickListener(view1 -> moodSection.highlightFunction("HAP", false));
        fragmentHomeBinding.fear.setOnClickListener(view1 -> moodSection.highlightFunction("FEA", false));
        fragmentHomeBinding.anger.setOnClickListener(view1 -> moodSection.highlightFunction("ANG", false));

        fragmentHomeBinding.tabLayoutMood.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mood_rating_type = Objects.requireNonNull(tab.getText()).toString().toLowerCase(Locale.ROOT);
                setRatingbarValue();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //do nothing
            }
        });

        fragmentHomeBinding.moodRatingSeekBar.setLabelFormatter(value -> Objects.requireNonNull(CommonClass.getSliderLabel(value)));
        fragmentHomeBinding.moodRatingSeekBar.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
                //do nothing
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                sleepSection.updateSleepDataToDB(String.valueOf(fragmentHomeBinding.moodRatingSeekBar.getValue()), mood_rating_type);
            }
        });

        fragmentHomeBinding.bedTime.setOnClickListener(view1 -> sleepSection.setTime(false, requireActivity().getSupportFragmentManager()));
        fragmentHomeBinding.wakeTime.setOnClickListener(view1 -> sleepSection.setTime(true, requireActivity().getSupportFragmentManager()));
        fragmentHomeBinding.editTextSleepNotes.setOnClickListener(view1 -> sleepSection.openSleepNotesDialog());

        return view;
    }

    public void setRatingbarValue() {
        try {
            String value = "0";
            switch (mood_rating_type) {
                case "morning":
                    value = !(sleepAndMoodModel.get(sleepAndMoodModel.size() - 1).getMorMood().isEmpty())
                            ? sleepAndMoodModel.get(sleepAndMoodModel.size() - 1).getMorMood() : "0";
                    break;
                case "noon":
                    value = !(sleepAndMoodModel.get(sleepAndMoodModel.size() - 1).getNoonMood().isEmpty())
                            ? sleepAndMoodModel.get(sleepAndMoodModel.size() - 1).getNoonMood() : "0";
                    break;
                case "evening":
                    value = !(sleepAndMoodModel.get(sleepAndMoodModel.size() - 1).getEveMood().isEmpty())
                            ? sleepAndMoodModel.get(sleepAndMoodModel.size() - 1).getEveMood() : "0";
                    break;
                default:
                    //do nothing
            }
            fragmentHomeBinding.moodRatingSeekBar.setValue(Float.parseFloat(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**

        FETCH DATA FROM THE DATABASE

     **/

    public static List<SleepAndMoodModel> sleepAndMoodModel = new ArrayList<>();

    public void fetchFromDBSleepMood(Context context, FragmentHomeBinding fragmentHomeBinding){
        PostModelFetching taskPostModel = new PostModelFetching(CommonClass.userModel.getEmail(), CommonClass.date, CommonClass.first_day);
        Call<List<SleepAndMoodModel>> call = DataApiController.getInstance()
                .getApiSleepMoodData()
                .fetchSleepMoodData(taskPostModel);
        call.enqueue(new Callback<List<SleepAndMoodModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<SleepAndMoodModel>> call, @NonNull Response<List<SleepAndMoodModel>> response) {
                if(response.body()!=null && response.isSuccessful()){
                    try {
                        if(response.body().get(0).getResponse()==null){
                            sleepAndMoodModel.clear();
                            sleepAndMoodModel.addAll(response.body());

                            fragmentHomeBinding.setDataObject(sleepAndMoodModel.get(CommonClass.day_number-1));

                            if(!sleepAndMoodModel.get(CommonClass.day_number-1).getUserMood().isEmpty()){
                                moodSection.highlightFunction(sleepAndMoodModel.get(CommonClass.day_number-1).getUserMood(), true);
                            }

                            setRatingbarValue();
                            moodSection.showMoodChart();
                            sleepSection.showSleepBarChart();
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
            public void onFailure(@NonNull Call<List<SleepAndMoodModel>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}