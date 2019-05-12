package com.example.healthtracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthtracker.core.People;
import java.lang.String;

public class BmiFragment extends Fragment {

    private People newPeople;
    private TextView result;
    private ProgressBar bmiBar;
    private EditText weight;
    private EditText height;
    private Button calculate;
    public BmiFragment() {
        // Required empty public constructor
         newPeople = new People(45, 1.59);
    }

    // TODO: Rename and change types and number of parameters
    public static BmiFragment newInstance(String param1, String param2) {
        BmiFragment fragment = new BmiFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        result = view.findViewById(R.id.result);
        bmiBar = view.findViewById(R.id.bmiBar);
        int tempResult = (int)Math.round(newPeople.getBMI());
        result.setText("calculated : " + tempResult);
        bmiBar.setProgress((int)Math.round(tempResult));

        height = view.findViewById(R.id.height);
        weight = view.findViewById(R.id.weight);
        calculate = view.findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                People deneme = new People(Integer.parseInt(weight.getText().toString()), Double.parseDouble(height.getText().toString()));
                //int tempResult = (int)Math.round(newPeople.getBMI());
                //result.setText(Integer.toString(tempResult));
                //bmiBar.setProgress(tempResult);
            }
        });



    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
