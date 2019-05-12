package com.example.healthtracker;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.healthtracker.core.PHT;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class PhtFragment extends Fragment {
    private LineChart chart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_pht, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final PHT myPHT=new PHT(this.getActivity());
        final EditText edit_text_sugar = (EditText)getView().findViewById(R.id.edit_text_blood_sugar);
        final EditText edit_text_heart = (EditText)getView().findViewById(R.id.edit_text_heart_rate);
        final EditText edit_text_tension = (EditText)getView().findViewById(R.id.edit_text_tension);
        final FloatingActionButton btn_blood = (FloatingActionButton) getView().findViewById(R.id.btn_blood_sugar);
        final FloatingActionButton btn_heart = (FloatingActionButton) getView().findViewById(R.id.btn_heart_rate);
        final FloatingActionButton btn_tension = (FloatingActionButton) getView().findViewById(R.id.btn_tension);
        btn_tension.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String content = edit_text_tension.getText().toString();
                myPHT.addTension(Integer.valueOf(content));

            }
        });
        btn_heart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String content = edit_text_heart.getText().toString();
                myPHT.addHeartRate(Integer.valueOf(content));
            }
        });
        btn_blood.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String content = edit_text_sugar.getText().toString();
                myPHT.addBloodSugar(Double.valueOf(content));
            }
        });

        chart = getView().findViewById(R.id.chart1);
        myPHT.drawGrap(chart);

    }


}
