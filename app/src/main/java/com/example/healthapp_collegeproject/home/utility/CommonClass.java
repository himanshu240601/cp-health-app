package com.example.healthapp_collegeproject.home.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.healthapp_collegeproject.databinding.FragmentHomeBinding;
import com.example.healthapp_collegeproject.home.models.UserModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class CommonClass {
    public static UserModel userModel;
    private final Context context;

    public CommonClass(Context context){
        this.context = context;
    }

    public void setUserModel() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LOGGED_INFO", Context.MODE_PRIVATE);
        userModel = new UserModel(
                sharedPreferences.getString("USER_NAME", ""),
                sharedPreferences.getString("USER_EMAIL", ""),
                sharedPreferences.getString("USER_PHONE", "")
        );
    }

    public static String date;
    public static String first_day;
    public static int day_number;

    @SuppressLint("SimpleDateFormat")
    public void setDate(){
        SimpleDateFormat simpleDateFormat;
        Calendar calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("EEE, MMM d");
        date = simpleDateFormat.format(calendar.getTime());
        getFirstDay();
        day_number = getDayNumber(date.substring(0, 3));
    }

    @SuppressLint("SimpleDateFormat")
    private void getFirstDay(){
        Calendar cal = Calendar.getInstance();
        String date = new SimpleDateFormat("EEE").format(cal.getTime());
        cal.add(Calendar.DATE, 1 - getDayNumber(date));
        first_day = new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());
    }

    public static int getDayNumber(String date){
        switch (date){
            case "Sun":
                return 1;
            case "Mon":
                return 2;
            case "Tue":
                return 3;
            case "Wed":
                return 4;
            case "Thu":
                return 5;
            case "Fri":
                return 6;
            case "Sat":
                return 7;
            default:
                break;
        }
        return -1;
    }

    @SuppressLint("SimpleDateFormat")
    public static double findTimeDifference(String s1, String s2) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy hh:mm a");
        Calendar cal = Calendar.getInstance();
        String curDate = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        String prevDate = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        if(s2.endsWith("pm")){
            cal.setTime(cal.getTime());
            cal.add(Calendar.DATE, -1);
            prevDate = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        }if(s1.endsWith("pm")){
            curDate = prevDate;
        }
        if(!s1.isEmpty() && !s2.isEmpty()){
            prevDate = prevDate+" "+s2;
            curDate = curDate+" "+s1;
            try{
                Date time1 = simpleDateFormat.parse(prevDate);
                Date time2 = simpleDateFormat.parse(curDate);

                long secInMills = 1000;
                long minInMills = secInMills*60;
                long houInMills = minInMills*60;

                long diff = Objects.requireNonNull(time2).getTime() - Objects.requireNonNull(time1).getTime();
                long elapsedHours = diff/houInMills;
                long elapsedMinutes = (diff/minInMills)%60;
                return Double.parseDouble(elapsedHours+"."+elapsedMinutes);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    //custom android chart images for x and y axis
    public static ArrayList<String> getMoodImages(){
        final String[] mood_images = {"", "😰", "😔", "😱", "😠", "😄"};
        return new ArrayList<>(Arrays.asList(mood_images).subList(0, 6));
    }

    //hours arraylist for graph axis
    public static ArrayList<String> getTotalHours(){
        final String[] hours = {"0h", "1h", "2h", "3h", "4h", "5h", "6h", "7h", "8h", "9h", "10h", "11h", "12h"};
        return new ArrayList<>(Arrays.asList(hours).subList(0, 13));
    }

    //weekdays string for graph axis
    public static ArrayList<String> getWeekDays(){
        final String[] days = {"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        return new ArrayList<>(Arrays.asList(days).subList(0, 8));
    }

    public static String getSliderLabel(float val){
        if(val>=0.0f && val<1.0f){
            return "Very Sad";
        }
        else if(val>=1.0f && val<2.0f){
            return "Sad";
        }
        else if(val>=2.0f && val<3.0f){
            return "Normal";
        }
        else if(val>=3.0f && val<4.0){
            return "Happy";
        }
        else if(val>=4.0f && val<=5.0){
            return "Very Happy";
        }
        return null;
    }

    public static String checkDayStatus(FragmentHomeBinding fragmentHomeBinding){
        Date date = new Date();
        int hours = date.getHours();
        String value = "";
        if(hours>=18 && hours<=24){
            Objects.requireNonNull(fragmentHomeBinding.tabLayoutMood.getTabAt(2)).select();
            value = Objects.requireNonNull(Objects.requireNonNull(fragmentHomeBinding.tabLayoutMood.getTabAt(2)).getText()).toString().toLowerCase(Locale.ROOT);
        }else if(hours>=12 && hours<18){
            Objects.requireNonNull(fragmentHomeBinding.tabLayoutMood.getTabAt(1)).select();
            value = Objects.requireNonNull(Objects.requireNonNull(fragmentHomeBinding.tabLayoutMood.getTabAt(1)).getText()).toString().toLowerCase(Locale.ROOT);
        }else{
            Objects.requireNonNull(fragmentHomeBinding.tabLayoutMood.getTabAt(0)).select();
            value = Objects.requireNonNull(Objects.requireNonNull(fragmentHomeBinding.tabLayoutMood.getTabAt(0)).getText()).toString().toLowerCase(Locale.ROOT);
        }
        return value;
    }
}

