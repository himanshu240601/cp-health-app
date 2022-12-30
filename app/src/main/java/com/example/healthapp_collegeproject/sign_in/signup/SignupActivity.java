package com.example.healthapp_collegeproject.sign_in.signup;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.ActivitySignupBinding;
import com.example.healthapp_collegeproject.sign_in.SignInCommon;
import com.example.healthapp_collegeproject.sign_in.login.LoginActivity;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding activitySignupBinding;

    private final SignInCommon signInCommon = new SignInCommon(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        activitySignupBinding.buttonContinue.setOnClickListener(view -> {

            removeFocus();

            activitySignupBinding.buttonContinue.setEnabled(false);

            String email_address = Objects.requireNonNull(activitySignupBinding.editTextEmailAddress.getText()).toString();
            String password = Objects.requireNonNull(activitySignupBinding.editTextPassword.getText()).toString();
            String confirm_password = Objects.requireNonNull(activitySignupBinding.editTextPasswordConfirm.getText()).toString();

            if(signInCommon.validateForm(email_address, password, confirm_password)){
                Intent intent = new Intent(this, SignupUserInfoActivity.class);
                intent.putExtra("email", email_address);
                intent.putExtra("password", password);
                startActivity(intent);
                activitySignupBinding.buttonContinue.setEnabled(true);
            }else{
                activitySignupBinding.buttonContinue.setEnabled(true);
            }
        });

        activitySignupBinding.logInLink.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        activitySignupBinding.backBtn.setOnClickListener(view-> finish());
    }

    /**

        removeFocus() - to remove focus from all text inputs

     **/

    private void removeFocus() {
        activitySignupBinding.textInputEmailAddress.clearFocus();
        activitySignupBinding.textInputPassword.clearFocus();
        activitySignupBinding.textInputPasswordConfirm.clearFocus();
    }
}