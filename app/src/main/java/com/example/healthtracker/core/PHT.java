package com.example.healthtracker.core;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class PHT {
    private Activity act;
    public PHT(Activity act){
        this.act=act;
    }
    public boolean addTension(int value){
        return addSomething(value,"tension");
    }
    public boolean addHeartRate(int value){
        return addSomething(value,"heart_rate");
    }
    public boolean addBloodSugar(double value){
        return addSomething(value,"blood_sugar");
    }
    public String getTension(){
        return getSomething("tension");
    }
    public String getHeartRate(){
        return getSomething("heart_rate");
    }
    public String getBloodSugar(){
        return getSomething("blood_sugar");
    }
    private boolean addSomething(double value,String whatis){
        SharedPreferences tension = this.act.getSharedPreferences(whatis+"Pref", 0);
        SharedPreferences.Editor editor = tension.edit();
        Date currentTime = Calendar.getInstance().getTime();
        String theThing=getSomething(whatis);
        if(theThing.length()>0){
            editor.putString(whatis,theThing+"~"+value+"~"+currentTime.toString());
        }else{
            editor.putString(whatis,value+"~"+currentTime.toString());
        }
        editor.apply();
        return false;
    }
    private String getSomething(String whatis){
        SharedPreferences thing = this.act.getSharedPreferences(whatis+"Pref", 0);
        String theThing = thing.getString(whatis,"");
        return theThing;
    }

}
