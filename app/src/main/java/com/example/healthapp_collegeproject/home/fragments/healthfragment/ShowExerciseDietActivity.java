package com.example.healthapp_collegeproject.home.fragments.healthfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthapp_collegeproject.R;

public class ShowExerciseDietActivity extends AppCompatActivity {

    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exercise_diet);

        initViews();

        backBtn.setOnClickListener(view -> finish());
    }

    private void initViews(){
        Intent intent = getIntent();

        ImageView image = findViewById(R.id.imageAsset);

        image.setImageResource(intent.getIntExtra("image", R.id.user_profile));

        backBtn = findViewById(R.id.backBtn);

        TextView title = findViewById(R.id.const_title);
        title.setText(intent.getStringExtra("title"));

        TextView subtitle = findViewById(R.id.const_time);
        subtitle.setText(intent.getStringExtra("subtitle"));

        TextView paragraph = findViewById(R.id.const_desc);
        paragraph.setText(intent.getStringExtra("description"));
    }
}