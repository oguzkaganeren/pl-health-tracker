package com.example.healthtracker.core;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PHT {
    private Activity act;
    private String tensions;
    private String bloodSugars;
    private String heartRates;
    private LineChart chart;
    public PHT(Activity act){
        this.act=act;
        this.bloodSugars=getBloodSugar();
        this.tensions=getTension();
        this.heartRates=getHeartRate();

    }
    public boolean addTension(int value){
        return addSomething(value,"tensions");
    }

    public boolean addHeartRate(int value){

        return addSomething(value,"heart_rates");
    }

    public boolean addBloodSugar(double value){
        return addSomething(value,"blood_sugars");
    }

    public String getTension(){
        return getSomething("tensions");
    }

    public String getHeartRate(){

        return getSomething("heart_rates");
    }

    public String getBloodSugar(){

        return getSomething("blood_sugars");
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
        return false;
    }
    private String getSomething(String whatis){
        SharedPreferences thing = this.act.getSharedPreferences(whatis+"Pref", 0);
        String theThing = thing.getString(whatis,"");
        return theThing;
    }
    public void drawGrap(LineChart chart){
        this.chart=chart;
        this.chart.setViewPortOffsets(50f, 0f, 0f, 0f);
        this.chart.setExtraOffsets(25f,0,0,0);
        this.chart.setBackgroundColor(Color.rgb(104, 241, 175));
        this.chart.setPadding(50,0,0,0);

        // no description text
        this.chart.getDescription().setEnabled(false);

        // enable touch gestures
        this.chart.setTouchEnabled(true);

        this.chart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        this.chart.setDragEnabled(true);
        this.chart.setScaleEnabled(true);
        this.chart.setDrawGridBackground(false);
        this.chart.setHighlightPerDragEnabled(true);
        // add data



        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setTextColor(Color.rgb(250, 250, 250));
        xAxis.setCenterAxisLabels(true);

        xAxis.setGranularity(1f); // one hour
        xAxis.setValueFormatter(new ValueFormatter() {

            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM HH:mm:ss", Locale.ENGLISH);

            @Override
            public String getFormattedValue(float value) {

                long millis = TimeUnit.HOURS.toMillis((long) value);
                return mFormat.format(new Date(millis));
            }
        });

        YAxis y = chart.getAxisLeft();
        y.setLabelCount(10, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        // add data
        addData();



        // don't forget to refresh the drawing
        chart.invalidate();
    }
    private void addData() {
        if(this.tensions.length()>0){
            String[] tensions=this.tensions.split("~");

            ArrayList<Entry> values = new ArrayList<>();
            Log.d("test", "addData: "+tensions.length);
            for (int i=0;i<tensions.length;i++)
            {
                values.add(new Entry(Long.valueOf(tensions[i].split("é")[1]),Float.valueOf(tensions[i].split("é")[0]) ) );

            }
            // create a dataset and give it a type
            LineDataSet set1 = new LineDataSet(values, "Tension");
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(250, 250, 250));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            this.chart.setData(data);
        }

    }
}
