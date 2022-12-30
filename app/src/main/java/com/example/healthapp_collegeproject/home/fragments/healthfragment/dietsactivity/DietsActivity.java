package com.example.healthapp_collegeproject.home.fragments.healthfragment.dietsactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.home.fragments.healthfragment.adpaters.DietClassAdapter;
import com.example.healthapp_collegeproject.home.fragments.healthfragment.models.ExerciseDietModel;

import java.util.ArrayList;

public class DietsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diets);

        findViewById(R.id.goBack).setOnClickListener(view -> finish());

        ArrayList<ExerciseDietModel> arrayList = new ArrayList<>();

        arrayList.add(new ExerciseDietModel(R.drawable.img_diet_1,"Healthy Yogurt", "Morning", "Chia seeds have a high concentration of omega-3 fatty acids. In fact, one tablespoon of chia seeds contains 1,769 milligrams of omega-3s. These nutrients play a role in the functioning of serotonin and dopamine, both critical to mood and mental health.\n" +
                "\n" +
                "Furthermore, chia seeds contain magnesium, which is sometimes referred to as “the stress antidote.” Some doctors and scientists believe that depression rates are increasing because we no longer get enough magnesium in our diet."));
        arrayList.add(new ExerciseDietModel(R.drawable.img_diet_2,"Leafy Vegetables", "Morning", "Walnuts are another rich, plant-based source of omega-3 fatty acids. In addition to impacting hormones, omega-3s support overall brain health. Deficiency in DHA (the chief omega) is associated with mental health disorders, including depression, ADHD, bipolar disorder, and schizophrenia.\n" +
                "\n" +
                "Moreover, numerous studies have demonstrated that omega-3 fatty acids support brain function and reduce depression symptoms.  "));
        arrayList.add(new ExerciseDietModel(R.drawable.img_diet_3,"Tomato Soup", "Noon", "Dark leafy greens like spinach, chard, kale, collard greens, and beet greens contain a wealth of nutrients, including the following:\n" +
                "\n" +
                "Iron \n" +
                "Calcium \n" +
                "Magnesium \n" +
                "Potassium \n" +
                "Folate\n" +
                "Omega-3 fatty acids\n" +
                "Folate (a form of vitamin B9), Magnesium, and Omega-3s all support mental health and brain health. Furthermore, studies show that deficiencies in folate may contribute to depression, fatigue, and insomnia. So, it’s important to get lots of leafy greens, among other foods you should eat everyday, in order to maintain healthy folate levels."));
        arrayList.add(new ExerciseDietModel(R.drawable.img_diet_4,"Bananas & Berries", "Afternoon", "Yogurt is a cultured (fermented) food. Therefore, it contains billions of probiotic bacteria, which make it an excellent food for mental as well as physical health. Probiotics help break down nutrients so the body can absorb them better. Consequently, we can digest our food better. As a result, the body and brain are able to more easily access the nutrients in our food. And recent research shows that our emotional well-being relies in part on information that travels from the gut to the brain. \n" +
                "\n" +
                "Consequently, researchers have found that people with healthy and diverse gut microbes are less likely to suffer from anxiety and depression. Moreover, studies show that having a healthy gut can reduce social anxiety and lower our reactions to stress."));
        arrayList.add(new ExerciseDietModel(R.drawable.img_diet_5, "Pulses", "Anytime", "Blueberries, raspberries, strawberries, blackberries, and other berries contain potent antioxidants. Therefore, they are particularly supportive for mental health. \n" +
                "\n" +
                "A review study of 115 articles confirmed the link between antioxidant levels and depression.\n" +
                "\n" +
                "In depressed patients, antioxidant levels were lower than those of a control group who did not have a mental health diagnosis. Furthermore, antioxidant levels increased following treatment for depression."));
        arrayList.add(new ExerciseDietModel(R.drawable.img_diet_6, "Eggs", "Afternoon","Avocados are another source of healthy fats, which help the brain function well. As a matter of fact, three-fourths of the calories of an avocado come from fat. This is mostly “good” fat, or monounsaturated fat, in the form of oleic acid. \n" +
                "\n" +
                "Moreover, avocados also contain tryptophan. Tryptophan is an amino acid that has a powerful impact on mood and mental health. It helps to balance hormones and aids in serotonin production. Consequently, eating foods containing tryptophan improves mood and overall mental stability."));
        arrayList.add(new ExerciseDietModel(R.drawable.img_diet_7, "Berries ", "Noon", "You might not have been expecting mushrooms on a list of foods you should eat everyday, but turns out they are a mental health superfood. Because they help to balance our blood sugar levels, they positively impact mood. When blood sugar is unbalanced, we experience a repeated series of highs and lows. Consequently, this creates unnecessary stress on the body and on our emotions.\n" +
                "\n" +
                "In addition, mushrooms promote healthy gut bacteria. And about 95 percent of our serotonin is produced in the gastrointestinal tract. Thus, a healthy gut supports healthy production of the hormones that control mood."));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.meaRecyclerView);
        recyclerView.setHasFixedSize(true);
        DietClassAdapter adapter = new DietClassAdapter(arrayList, DietsActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(DietsActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}