package com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.example.healthapp_collegeproject.R;
import com.example.healthapp_collegeproject.databinding.CustomDialogBoxBinding;
import com.example.healthapp_collegeproject.databinding.FragmentHomeBinding;
import com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.models.TasksDataModel;
import com.example.healthapp_collegeproject.home.fragments.homefragment.tasks_section.tasks_database.TaskDataBaseClass;
import com.example.healthapp_collegeproject.home.utility.CommonClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TasksSection {

    private final Context context;
    private final FragmentHomeBinding fragmentHomeBinding;
    private final View view;

    public TasksSection(Context context, FragmentHomeBinding fragmentHomeBinding, View view){
        this.context = context;
        this.fragmentHomeBinding = fragmentHomeBinding;
        this.view = view;
    }

    private TextView[] weekDays;

    private TextView[] textViewSun;
    private TextView[] textViewMon;
    private TextView[] textViewTue;
    private TextView[] textViewWed;
    private TextView[] textViewThu;
    private TextView[] textViewFri;
    private TextView[] textViewSat;


    @SuppressLint("SetTextI18n")
    private void fillPrevTasks(TextView[] textViewDay, int day) {
        for(int j=0;j<6;j++){
            if (day < CommonClass.day_number) {
                textViewDay[j].setCompoundDrawables(null, null, null, null);
                textViewDay[j].setText("Empty");
            }
        }

        if(!TaskDataBaseClass.tasksDataModels.isEmpty()){

            List<TasksDataModel> tasksDataModelList = new ArrayList<>(TaskDataBaseClass.tasksDataModels);

            if(day<=CommonClass.day_number){
                if(!tasksDataModelList.get(day-1).getWake10am().equals("")){
                    textViewDay[0].setText(tasksDataModelList.get(day-1).getWake10am());
                }
                if(!tasksDataModelList.get(day-1).get_10amNoon().equals("")){
                    textViewDay[1].setText(tasksDataModelList.get(day-1).get_10amNoon());
                }
                if(!tasksDataModelList.get(day-1).getNoon3pm().equals("")){
                    textViewDay[2].setText(tasksDataModelList.get(day-1).getNoon3pm());
                }
                if(!tasksDataModelList.get(day-1).get_3pm5pm().equals("")){
                    textViewDay[3].setText(tasksDataModelList.get(day-1).get_3pm5pm());
                }
                if(!tasksDataModelList.get(day-1).get_5pm8pm().equals("")){
                    textViewDay[4].setText(tasksDataModelList.get(day-1).get_5pm8pm());
                }
                if(!tasksDataModelList.get(day-1).get_8pmSleep().equals("")){
                    textViewDay[5].setText(tasksDataModelList.get(day-1).get_8pmSleep());
                }
            }
        }
    }

    public void setCompletedDaysText(int day){
        for(int i=0;i<=day;i++){
            if(i==0){
                fillPrevTasks(textViewSun, i+1);
            }else if(i==1){
                fillPrevTasks(textViewMon, i+1);
            }else if(i==2){
                fillPrevTasks(textViewTue, i+1);
            }else if(i==3){
                fillPrevTasks(textViewWed, i+1);
            }else if(i==4){
                fillPrevTasks(textViewThu, i+1);
            }else if(i==5){
                fillPrevTasks(textViewFri, i+1);
            }else if(i==6){
                fillPrevTasks(textViewSat, i+1);
            }
        }
    }

    public static TextView[] todayTextView;

    private void createDropDownMenu(TextView [] idArray) {
        todayTextView = new TextView[idArray.length];
        for(int i=0;i<todayTextView.length;i++){
            todayTextView[i] = idArray[i];
            todayTextView[i].setGravity(Gravity.CENTER_VERTICAL);
        }

        todayTextView[0].setOnClickListener(view1 -> showMultiSelectDialog(0, "Wake - 10 AM"));
        todayTextView[1].setOnClickListener(view1 -> showMultiSelectDialog(1, "10 AM - Noon"));
        todayTextView[2].setOnClickListener(view1 -> showMultiSelectDialog(2, "Noon - 3 PM"));
        todayTextView[3].setOnClickListener(view1 -> showMultiSelectDialog(3, "3 PM - 5 PM"));
        todayTextView[4].setOnClickListener(view1 -> showMultiSelectDialog(4, "5 PM - 8 PM"));
        todayTextView[5].setOnClickListener(view1 -> showMultiSelectDialog(5, "8 PM - Sleep"));
    }

    private void createDropdownCells(int day){

        TableRow[] tableRows = new TableRow[]{
                view.findViewById(R.id.tableRowSunday),
                view.findViewById(R.id.tableRowMonday),
                view.findViewById(R.id.tableRowTuesday),
                view.findViewById(R.id.tableRowWednesday),
                view.findViewById(R.id.tableRowThursday),
                view.findViewById(R.id.tableRowFriday),
                view.findViewById(R.id.tableRowSaturday)
        };

        for(int i=0;i<=day;i++){
            tableRows[i].setVisibility(View.VISIBLE);
        }

        weekDays[day].setTextColor(ContextCompat.getColor(context, R.color.blue));

        setCompletedDaysText(day);
    }

    private void resetWeekDayTextViewColor(){

        weekDays = new TextView[]{
                view.findViewById(R.id.textViewSun),
                view.findViewById(R.id.textViewMon),
                view.findViewById(R.id.textViewTue),
                view.findViewById(R.id.textViewWed),
                view.findViewById(R.id.textViewThu),
                view.findViewById(R.id.textViewFri),
                view.findViewById(R.id.textViewSat)
        };

        for (TextView textViewWeekDay : weekDays) {
            textViewWeekDay.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

    }

    public void setTableRows(){

        resetWeekDayTextViewColor();

        initializeTextViews();

        switch (CommonClass.date.substring(0,3)){
            case "Sun":
                createDropdownCells(0);
                createDropDownMenu(textViewSun);
                break;
            case "Mon":
                createDropdownCells(1);
                createDropDownMenu(textViewMon);
                break;
            case "Tue":
                createDropdownCells(2);
                createDropDownMenu(textViewTue);
                break;
            case "Wed":
                createDropdownCells(3);
                createDropDownMenu(textViewWed);
                break;
            case "Thu":
                createDropdownCells(4);
                createDropDownMenu(textViewThu);
                break;
            case "Fri":
                createDropdownCells(5);
                createDropDownMenu(textViewFri);
                break;
            case "Sat":
                createDropdownCells(6);
                createDropDownMenu(textViewSat);
                break;
            default:
                break;
        }
    }

    private void initializeTextViews(){
        textViewSun = new TextView[]{
                view.findViewById(R.id.sunTextView1),
                view.findViewById(R.id.sunTextView2),
                view.findViewById(R.id.sunTextView3),
                view.findViewById(R.id.sunTextView4),
                view.findViewById(R.id.sunTextView5),
                view.findViewById(R.id.sunTextView6)
        };
        textViewMon = new TextView[]{
                view.findViewById(R.id.monTextView1),
                view.findViewById(R.id.monTextView2),
                view.findViewById(R.id.monTextView3),
                view.findViewById(R.id.monTextView4),
                view.findViewById(R.id.monTextView5),
                view.findViewById(R.id.monTextView6)
        };
        textViewTue = new TextView[]{
                view.findViewById(R.id.tueTextView1),
                view.findViewById(R.id.tueTextView2),
                view.findViewById(R.id.tueTextView3),
                view.findViewById(R.id.tueTextView4),
                view.findViewById(R.id.tueTextView5),
                view.findViewById(R.id.tueTextView6)
        };
        textViewWed = new TextView[]{
                view.findViewById(R.id.wedTextView1),
                view.findViewById(R.id.wedTextView2),
                view.findViewById(R.id.wedTextView3),
                view.findViewById(R.id.wedTextView4),
                view.findViewById(R.id.wedTextView5),
                view.findViewById(R.id.wedTextView6)
        };
        textViewThu = new TextView[]{
                view.findViewById(R.id.thuTextView1),
                view.findViewById(R.id.thuTextView2),
                view.findViewById(R.id.thuTextView3),
                view.findViewById(R.id.thuTextView4),
                view.findViewById(R.id.thuTextView5),
                view.findViewById(R.id.thuTextView6)
        };
        textViewFri = new TextView[]{
                view.findViewById(R.id.friTextView1),
                view.findViewById(R.id.friTextView2),
                view.findViewById(R.id.friTextView3),
                view.findViewById(R.id.friTextView4),
                view.findViewById(R.id.friTextView5),
                view.findViewById(R.id.friTextView6)
        };
        textViewSat = new TextView[]{
                view.findViewById(R.id.satTextView1),
                view.findViewById(R.id.satTextView2),
                view.findViewById(R.id.satTextView3),
                view.findViewById(R.id.satTextView4),
                view.findViewById(R.id.satTextView5),
                view.findViewById(R.id.satTextView6)
        };
    }



    private final TaskDataBaseClass taskDataBaseClass = new TaskDataBaseClass();

    private boolean[] selectedTasks;
    private ArrayList<Integer> taskList;
    public void showMultiSelectDialog(
            int index, String time){
        ArrayList<String> taskListStr = new ArrayList<>();
        taskList = new ArrayList<>();
        String[] taskArray = context.getResources().getStringArray(R.array.tasks_array);
        selectedTasks = new boolean[taskArray.length];
        ArrayList<String> arrayListOth = new ArrayList<>();
        //initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(
                context
        );
        //set title
        builder.setTitle(R.string.select_tasks);
        //set dialog non cancelable
        builder.setCancelable(false);

        builder.setMultiChoiceItems(taskArray, selectedTasks, (dialogInterface, i, b) -> {
            //check condition
            if(b){
                //when checkbox selected
                //add position in day list
                if(taskArray[i].equals(context.getString(R.string.others))){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    ViewGroup viewGroup = view.findViewById(android.R.id.content);
                    CustomDialogBoxBinding customDialogBoxBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_dialog_box, viewGroup, false);
                    builder1.setView(customDialogBoxBinding.getRoot());
                    AlertDialog alertDialog = builder1.create();

                    customDialogBoxBinding.textInputDialog.setHint(R.string.add_task1_task2);

                    customDialogBoxBinding.saveButton.setOnClickListener(view12 -> {
                        String value = Objects.requireNonNull(customDialogBoxBinding.editTextDialog.getText()).toString();
                        if(!value.isEmpty()){
                            arrayListOth.add(value);
                            alertDialog.dismiss();
                        }else{
                            Toast.makeText(context,context.getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
                        }
                    });
                    customDialogBoxBinding.cancelButton.setOnClickListener(view1 -> {
                        arrayListOth.clear();
                        alertDialog.dismiss();
                    });
                    alertDialog.show();
                }else{
                    taskList.add(i);
                }
            }else{
                //when checkbox unselected
                //remove position from day list
                if(taskArray[i].equals(context.getString(R.string.others))){
                    arrayListOth.clear();
                }
                taskList.remove(Integer.valueOf(i));
            }
        });

        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            //initialize the string builder
            StringBuilder stringBuilder = new StringBuilder();
            //use for loop
            for(int j=0;j<taskList.size();j++){

                stringBuilder.append(taskArray[taskList.get(j)]);
                taskListStr.add(taskArray[taskList.get(j)]);
                //check condition
                if(j!=taskList.size()-1){
                    //when j value not equal to day list size -1
                    //add comma
                    stringBuilder.append(", ");
                }
            }
            if(!arrayListOth.isEmpty()){
                if(stringBuilder.length()!=0){
                    stringBuilder.append(", ");
                }
                stringBuilder.append(arrayListOth.get(0));
            }
            //set text on text view
            todayTextView[index].setText(stringBuilder.toString());
            taskDataBaseClass.updateTasksDataToDB(context, time, stringBuilder.toString(), fragmentHomeBinding, this);
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            //dismiss dialog
            dialogInterface.dismiss();
        });

        builder.setNeutralButton("Clear", (dialogInterface, i) -> {
            //use for loop
            for (int j=0;j<selectedTasks.length;j++){
                //remove all selected boxes
                selectedTasks[j] = false;
                //clear task list
                taskList.clear();
                taskListStr.clear();
                //clear text view
                TasksSection.todayTextView[index].setText(null);
            }
        });
        //show dialog
        builder.show();
    }
}
