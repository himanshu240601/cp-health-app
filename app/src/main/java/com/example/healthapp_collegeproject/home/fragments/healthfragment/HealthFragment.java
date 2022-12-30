package com.example.healthapp_collegeproject.home.fragments.healthfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.home.fragments.healthfragment.dietsactivity.DietsActivity;
import com.example.healthapp_collegeproject.home.fragments.healthfragment.workoutsactivity.WorkoutsActivity;

public class HealthFragment extends Fragment {

    private LinearLayout mealsCard, workoutsCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health, container, false);

        initViews(view);

        mealsCard.setOnClickListener(view12 -> startActivity(new Intent(requireContext(), DietsActivity.class)));

        workoutsCard.setOnClickListener(view1 -> startActivity(new Intent(requireContext(), WorkoutsActivity.class)));

        return view;
    }

    private void initViews(View view){
        mealsCard = view.findViewById(R.id.meal_plans);
        workoutsCard = view.findViewById(R.id.workout_plans);
    }
}