package com.example.healthtracker;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.healthtracker.core.PHT;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class PhtFragment extends Fragment {

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
    }

}
