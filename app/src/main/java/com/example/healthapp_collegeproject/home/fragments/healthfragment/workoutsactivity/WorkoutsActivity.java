package com.example.healthapp_collegeproject.home.fragments.healthfragment.workoutsactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.home.fragments.healthfragment.adpaters.ExerciseClassAdapter;
import com.example.healthapp_collegeproject.home.fragments.healthfragment.models.ExerciseDietModel;

import java.util.ArrayList;

public class WorkoutsActivity extends AppCompatActivity {

    private ArrayList<ExerciseDietModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        findViewById(R.id.goBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        arrayList = new ArrayList<>();

        arrayList.add(new ExerciseDietModel(R.drawable.yoga_cat_cow,"Seated Cat Cow", "00:40 Sec", "Sit cross-legged and rest your hands on your knees. As you inhale, lift your gaze and try to drive your chest out.\n\nOn an exhale, tuck your chin, draw your lower belly in and round your back. Repeat the exercise."));
        arrayList.add(new ExerciseDietModel(R.drawable.yoga_lunge_left,"Low Lunge Left", "00:40 Sec","Start in a lunge position, but with your right leg on the floor. Tuck your pelvis in and sink your hips.\n\nRaise your arms up by your ears with your palms facing each other. Look straight ahead. Hold this position."));
        arrayList.add(new ExerciseDietModel(R.drawable.yoga_downward_dog,"Downward Dog", "00:40 Sec","Start on all fours with your knees under your buttocks and your hands directly under your shoulders.\n\nLift your sit bones up and back with your knees bent. Draw your lower abdomen in to support the spine.\n\nSpread your fingers and slightly spread your feet apart. Hold this position."));
        arrayList.add(new ExerciseDietModel(R.drawable.yoga_lunge_right,"Low Lunge Right", "00:40 Sec","Start in a lunge position, but with your left leg on the floor. Tuck your pelvis in and sink your hips.\n\nRaise your arms up by your ears with your palms facing each other. Look straight ahead. Hold this position."));
        arrayList.add(new ExerciseDietModel(R.drawable.yoga_sphinx_pose,"Sphinx Pose", "01:00 Min","Lie on your stomach with your forearms on the floor and your elbows under your shoulders. Extend your legs with the tops of your feet on the floor.\n\nInhale, lift your upper body and extend your neck from your shoulders. Hold this position."));
        arrayList.add(new ExerciseDietModel(R.drawable.yoga_childs_pose,"Child's Pose", "01:00 Min","Start with your knees and hands on the floor. Put your hands a little forward, widen your knees and put your toes together.\n\nTake a breath, then exhale and sit back. Try to make your butt touch your heels. Relax your elbows, make your forehead touch the floor and try to lower your chest close to the floor. Hold this position.\n\nKeep your arms stretched forward as you sit back. Make sure there is enough space between your shoulders and ears during the exercise."));
        arrayList.add(new ExerciseDietModel(R.drawable.yoga_corpse_pose,"Corpse Pose", "01:00 Min","Lie on your back with your legs stretched, hands at your sides and palms up. Take deep relaxation on the floor."));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.exeRecyclerView);
        recyclerView.setHasFixedSize(true);
        ExerciseClassAdapter adapter = new ExerciseClassAdapter(arrayList, WorkoutsActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(WorkoutsActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}