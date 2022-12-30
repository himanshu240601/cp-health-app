package com.example.healthapp_collegeproject.home.fragments.settingsfragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.FragmentSettingsBinding;
import com.example.healthapp_collegeproject.home.fragments.settingsfragment.aboutactivity.AboutActivity;
import com.example.healthapp_collegeproject.home.fragments.settingsfragment.profileactivity.ProfileActivity;
import com.example.healthapp_collegeproject.home.utility.CommonClass;
import com.example.healthapp_collegeproject.sign_in.SignInActivity;

public class SettingsFragment extends Fragment {

    FragmentSettingsBinding fragmentSettingsBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSettingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);

        fragmentSettingsBinding.setUserObject(CommonClass.userModel);

        fragmentSettingsBinding.myProfileButton.setOnClickListener(view -> startActivity(new Intent(requireContext(), ProfileActivity.class)));

        fragmentSettingsBinding.aboutSetting.setOnClickListener(view -> startActivity(new Intent(requireContext(), AboutActivity.class)));

        fragmentSettingsBinding.logOutButton.setOnClickListener(view -> new AlertDialog.Builder(requireContext())
                .setTitle("Log Out")
                .setMessage("Do you really want to logout?")
                .setIcon(R.drawable.icon_about)
                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("LOGGED_INFO", Context.MODE_PRIVATE);
                    sharedPreferences.edit().clear().apply();
                    requireActivity().finishAffinity();
                    Intent intent = new Intent(requireContext(), SignInActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton(android.R.string.no, null)
                .show());


        return fragmentSettingsBinding.getRoot();
    }
}