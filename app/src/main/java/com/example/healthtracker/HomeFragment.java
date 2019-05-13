package com.example.healthtracker;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthtracker.core.PHT;
import com.github.mikephil.charting.charts.BarChart;


public class HomeFragment extends Fragment {
    private BarChart tensionChart;
    private BarChart bloodChart;
    private BarChart heartChart;
    private BarChart weightChart;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final PHT myPHT=new PHT(this.getActivity());
        tensionChart = getView().findViewById(R.id.tensionChart);
        bloodChart = getView().findViewById(R.id.bloodChart);
        heartChart = getView().findViewById(R.id.heartChart);
        weightChart = getView().findViewById(R.id.weightChart);
        myPHT.drawGrap(tensionChart,"tensions", Color.CYAN);
        myPHT.drawGrap(bloodChart,"blood sugars",Color.RED);
        myPHT.drawGrap(heartChart,"heart rates",Color.MAGENTA);
        myPHT.drawGrap(weightChart,"weights",Color.GREEN);
    }
}
