package com.example.healthtracker;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.alespero.expandablecardview.ExpandableCardView;
import com.example.healthtracker.core.EER;
import com.example.healthtracker.core.Person;

import java.lang.String;
import com.alespero.expandablecardview.ExpandableCardView;
import com.example.healthtracker.core.Person;

import java.lang.String;


public class EerFragment extends Fragment {

    private Person newPeople;
    private EER newEER;
    public EerFragment() {
        // Required empty public constructor
        newPeople = new Person(45.1, 159);
        newEER = new EER(newPeople);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String[] values= {"Sedentary","Low Active", "Active", "Very Active"};

        final NumberPicker PA = view.findViewById(R.id.eerPick);
        final TextView eerText = view.findViewById(R.id.EerText);
        final TextView resultText = view.findViewById(R.id.resultText);
        PA.setMinValue(0);
        PA.setMaxValue(values.length-1);
        PA.setDisplayedValues(values);
        PA.setWrapSelectorWheel(true);


        PA.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                eerText.setText("Physical Activity Coefficients : " + values[newVal]);
                newPeople.setEer(values[newVal]);
                resultText.setText("Estimated Energy Requirement(kcal/day) :" + Double.toString(newEER.getResult()));
            }
        });


    }




    // TODO: Rename and change types and number of parameters
    public static EerFragment newInstance(String param1, String param2) {
        EerFragment fragment = new EerFragment();
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
        return inflater.inflate(R.layout.fragment_eer, container, false);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
