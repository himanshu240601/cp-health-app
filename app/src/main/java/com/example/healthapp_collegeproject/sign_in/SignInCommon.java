package com.example.healthapp_collegeproject.sign_in;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.sign_in.signup.models.SignUpUserModel;

public class SignInCommon {
    private final Context context;

    public SignInCommon(Context context){
        this.context = context;
    }

    public void saveDataToSharedPrefs(String email_address, SignUpUserModel body){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LOGGED_INFO", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("IS_LOGGED", true).apply();
        sharedPreferences.edit().putString("USER_EMAIL", email_address).apply();
        sharedPreferences.edit().putString("USER_NAME", body.getUser_name()).apply();
        sharedPreferences.edit().putString("USER_PHONE", body.getUser_phone()).apply();
    }

    public boolean validateForm(String string1, String string2, Boolean isPhone){
        if(isPhone){
            if(string2.isEmpty()){
                Toast.makeText(context, context.getString(R.string.name_required)+"*", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(string1.isEmpty()){
                Toast.makeText(context, context.getString(R.string.mobile_number_required)+"*", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if(string1.length()<10) {
                Toast.makeText(context, R.string.invalid_mobile_number, Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            if(string1.isEmpty()){
                Toast.makeText(context, context.getString(R.string.email_address_required)+"*", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if(!string1.endsWith("@gmail.com")) {
                Toast.makeText(context, context.getString(R.string.email_must_end_with_gmail_com), Toast.LENGTH_SHORT).show();
                return false;
            }

            if(string2.isEmpty()){
                Toast.makeText(context, context.getString(R.string.password_required)+"*", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public boolean validateForm(String email_address, String password, String confirm_password) {
        if (email_address.isEmpty()) {
            Toast.makeText(context, context.getString(R.string.email_address_required)+"*", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!email_address.endsWith("@gmail.com")) {
            Toast.makeText(context, context.getString(R.string.email_must_end_with_gmail_com), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty()) {
            Toast.makeText(context, context.getString(R.string.password_required)+"*", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.length() < 8) {
            Toast.makeText(context, context.getString(R.string.password_length_too_short), Toast.LENGTH_SHORT).show();
            return false;
        }

        if(confirm_password.isEmpty()){
            Toast.makeText(context, context.getString(R.string.confirm_password_required)+"*", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!confirm_password.equals(password)){
            Toast.makeText(context, context.getString(R.string.passwords_does_not_match), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
