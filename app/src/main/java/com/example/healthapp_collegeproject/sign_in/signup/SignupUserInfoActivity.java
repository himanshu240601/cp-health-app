package com.example.healthapp_collegeproject.sign_in.signup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.ActivitySignupUserInfoBinding;
import com.example.healthapp_collegeproject.home.HomeActivity;
import com.example.healthapp_collegeproject.sign_in.SignInCommon;
import com.example.healthapp_collegeproject.sign_in.api_controller.SignInApiController;
import com.example.healthapp_collegeproject.sign_in.signup.models.ResponseModel;
import com.example.healthapp_collegeproject.sign_in.signup.models.SignUpUserModel;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupUserInfoActivity extends AppCompatActivity {

    private ActivitySignupUserInfoBinding activitySignupUserInfoBinding;

    private final SignInCommon signInCommon = new SignInCommon(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupUserInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup_user_info);

        Intent intent = getIntent();
        String email_address = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        activitySignupUserInfoBinding.backBtn.setOnClickListener(view-> finish());

        activitySignupUserInfoBinding.buttonSignUp.setOnClickListener(view->{

            removeFocus();

            activitySignupUserInfoBinding.buttonSignUp.setEnabled(false);

            String name = Objects.requireNonNull(activitySignupUserInfoBinding.editTextUserName.getText()).toString();
            String phone = Objects.requireNonNull(activitySignupUserInfoBinding.editTextUserPhone.getText()).toString();

            if(signInCommon.validateForm(phone, name, true)){
                signInUser(email_address, password, name, phone);
            }else{
                activitySignupUserInfoBinding.buttonSignUp.setEnabled(true);
            }
        });
    }

    private void signInUser(String email_address, String password, String name, String phone) {
        SignUpUserModel signUpUserModel = new SignUpUserModel(password, email_address, name, phone);
        Call<ResponseModel> call = SignInApiController.getInstance()
                .getApiCreateUser()
                .createUser(signUpUserModel);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                if(response.body()!=null && response.isSuccessful()){
                    try {
                        if(response.body().getResponse().equals("Signup success")){
                            signInCommon.saveDataToSharedPrefs(email_address, signUpUserModel);
                            startActivity(new Intent(SignupUserInfoActivity.this, HomeActivity.class));
                            finish();
                        }else{
                            Toast.makeText(SignupUserInfoActivity.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                            activitySignupUserInfoBinding.buttonSignUp.setEnabled(true);
                            finish();
                        }

                    } catch (Exception e) {
                        Toast.makeText(SignupUserInfoActivity.this, "Error Please Try Again!", Toast.LENGTH_SHORT).show();
                        activitySignupUserInfoBinding.buttonSignUp.setEnabled(true);
                        finish();
                    }
                }else{
                    Toast.makeText(SignupUserInfoActivity.this, "An Error Occurred, Please Try Again!", Toast.LENGTH_SHORT).show();
                    activitySignupUserInfoBinding.buttonSignUp.setEnabled(true);
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Toast.makeText(SignupUserInfoActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                activitySignupUserInfoBinding.buttonSignUp.setEnabled(true);
                finish();
            }
        });
    }

    /**

     removeFocus() - to remove focus from all text inputs

     **/

    private void removeFocus() {
        activitySignupUserInfoBinding.textInputUserName.clearFocus();
        activitySignupUserInfoBinding.textInputUserPhone.clearFocus();
    }
}