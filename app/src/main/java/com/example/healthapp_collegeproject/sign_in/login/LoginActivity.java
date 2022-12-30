package com.example.healthapp_collegeproject.sign_in.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.ActivityLoginBinding;
import com.example.healthapp_collegeproject.home.HomeActivity;
import com.example.healthapp_collegeproject.sign_in.SignInCommon;
import com.example.healthapp_collegeproject.sign_in.api_controller.SignInApiController;
import com.example.healthapp_collegeproject.sign_in.login.models.LogInUserModel;
import com.example.healthapp_collegeproject.sign_in.signup.SignupActivity;
import com.example.healthapp_collegeproject.sign_in.signup.models.SignUpUserModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding activityLoginBinding;

    private final SignInCommon signInCommon = new SignInCommon(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        activityLoginBinding.buttonLogIn.setOnClickListener(view -> {

            removeFocus();

            activityLoginBinding.buttonLogIn.setEnabled(false);

            String email_address = Objects.requireNonNull(activityLoginBinding.editTextEmailAddress.getText()).toString();
            String password = Objects.requireNonNull(activityLoginBinding.editTextPassword.getText()).toString();

            if(signInCommon.validateForm(email_address, password, false)) {
                logInUser(email_address, password);
            }else{
                activityLoginBinding.buttonLogIn.setEnabled(true);
            }
        });

        activityLoginBinding.signUpLink.setOnClickListener(view -> {
            startActivity(new Intent(this, SignupActivity.class));
            finish();
        });

        activityLoginBinding.backBtn.setOnClickListener(view-> finish());
    }

    private void logInUser(String email_address, String password) {
        LogInUserModel logInUserModel = new LogInUserModel(email_address, password);
        Call<SignUpUserModel> call = SignInApiController.getInstance()
                .getApiLoginUser()
                .loginUser(logInUserModel);
        call.enqueue(new Callback<SignUpUserModel>() {
            @Override
            public void onResponse(@NonNull Call<SignUpUserModel> call, @NonNull Response<SignUpUserModel> response) {
                if(response.body()!=null && response.isSuccessful()){
                    try {
                        if(response.body().getResponse().equals("Login success")){
                            signInCommon.saveDataToSharedPrefs(email_address, response.body());
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                            activityLoginBinding.buttonLogIn.setEnabled(true);
                        }

                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Error Please Try Again!", Toast.LENGTH_SHORT).show();
                        activityLoginBinding.buttonLogIn.setEnabled(true);
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "An Error Occurred, Please Try Again!", Toast.LENGTH_SHORT).show();
                    activityLoginBinding.buttonLogIn.setEnabled(true);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SignUpUserModel> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                activityLoginBinding.buttonLogIn.setEnabled(true);
            }
        });
    }


    /**

     removeFocus() - to remove focus from all text inputs

     **/

    private void removeFocus() {
        activityLoginBinding.textInputEmailAddress.clearFocus();
        activityLoginBinding.textInputPassword.clearFocus();
    }
}