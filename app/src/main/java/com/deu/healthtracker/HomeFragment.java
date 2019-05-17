package com.deu.healthtracker;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deu.healthtracker.core.PHT;
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
        myPHT.drawGrap(tensionChart,"tensions", Color.rgb(255, 193, 7));
        myPHT.drawGrap(bloodChart,"blood sugars",Color.rgb(255, 87, 34));
        myPHT.drawGrap(heartChart,"heart rates",Color.rgb(139, 195, 74));
        myPHT.drawGrap(weightChart,"weights",Color.rgb(255, 235, 59));
    }
}
