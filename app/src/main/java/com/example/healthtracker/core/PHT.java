package com.example.healthtracker.core;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PHT {
    private Activity act;
    public PHT(Activity act){
        this.act=act;

    }
    public boolean addTension(int value){
        if(value<40||value>400){
            return false;
        }
        return addSomething(value,"tensions");
    }

    public boolean addHeartRate(int value){
        if(value<40||value>300){
            return false;
        }
        return addSomething(value,"heart rates");
    }
    public boolean addWeight(double value){
        if(value<30||value>450){
            return false;
        }
        return addSomething(value,"weights");
    }
    public boolean addBloodSugar(double value){

        if(value<0||value>25){
            return false;
        }
        return addSomething(value,"blood sugars");
    }

    public String getTension(){
        return getSomething("tensions");
    }

    public String getHeartRate(){

        return getSomething("heart rates");
    }
    public String getWeight(){

        return getSomething("weights");
    }
    public String getBloodSugar(){

        return getSomething("blood sugars");
    }

    private void clearData(String whatis){
        SharedPreferences smt = this.act.getSharedPreferences(whatis+"Pref", 0);
        smt.edit().clear().commit();
    }
    private boolean addSomething(double value,String whatis){
        SharedPreferences smt = this.act.getSharedPreferences(whatis+"Pref", 0);
        SharedPreferences.Editor editor = smt.edit();
        long currentTime = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis());
        String theThing=getSomething(whatis);
        if(theThing.length()>0){
            editor.putString(whatis,theThing+"~"+value+"é"+currentTime);
        }else{
            editor.putString(whatis,value+"é"+currentTime);
        }
        editor.apply();
        return true;
    }
    private String getSomething(String whatis){
        SharedPreferences thing = this.act.getSharedPreferences(whatis+"Pref", 0);
        String theThing = thing.getString(whatis,"");
        return theThing;
    }
    public void drawGrap(BarChart chart,String which,int color){
        chart.setBackgroundColor(Color.parseColor("#FAFAFA"));

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setBorderColor(Color.DKGRAY);
        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.DKGRAY);


        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setZeroLineWidth(0);
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.DKGRAY);
        leftAxis.setDrawZeroLine(false);
        chart.getAxisRight().setEnabled(false);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setTextColor(Color.DKGRAY);
        l.setDrawInside(true);
        xAxis.setValueFormatter(new ValueFormatter() {

            private final SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);

            @Override
            public String getFormattedValue(float value) {

                long millis = TimeUnit.HOURS.toMillis((long) value);
                return mFormat.format(new Date(millis));
            }
        });

        // add data
        addData(which,chart,color);

        // don't forget to refresh the drawing
        chart.invalidate();
    }
    private void addData(String which,BarChart chart,int color) {
        String savedData=getSomething(which);
        if(savedData.length()>0){
            ArrayList<BarEntry> values = new ArrayList<>();
            String[] myData=savedData.split("~");

            Log.d("test", "addData: "+myData.length);
            for (int i=0;i<myData.length;i++)
            {
                values.add(new BarEntry(Long.valueOf(myData[i].split("é")[1]),Float.valueOf(myData[i].split("é")[0]) ) );

            }
            // create a dataset and give it a type
            BarDataSet set1;
                set1 = new BarDataSet(values, "Your "+which);

                set1.setDrawIcons(false);

            set1.setColor(color);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.3f);
            chart.getXAxis().setSpaceMax(5f);
            chart.setData(data);
        }

    }
}
