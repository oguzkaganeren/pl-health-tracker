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
import com.example.healthtracker.core.Person;
import com.github.anastr.speedviewlib.PointerSpeedometer;

import java.lang.String;

public class BmiFragment extends Fragment {

    private Person currentUser;
    //private ProgressBar bmiBar;

    public BmiFragment() {
        // Required empty public constructor
        currentUser = MainActivity.user;
    }
    int full = 0, half = 0; double currentWeight = 0.0;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final PointerSpeedometer speedometer = (PointerSpeedometer)view.findViewById(R.id.speedView);
        final NumberPicker weightFull = view.findViewById(R.id.weightFull);
        final NumberPicker weightDecimal = view.findViewById(R.id.weightDecimal);
        final ExpandableCardView setWeight = view.findViewById(R.id.setWeight);
        final TextView tx = view.findViewById(R.id.result);
        //tx.setText(""+currentUser.getBMI());
        setWeight.setTitle("Current Weight : " +currentUser.getWeight());

        weightDecimal.setValue((int)((int)currentUser.getWeight() - currentUser.getWeight()));
        weightFull.setMinValue(30);
        weightFull.setMaxValue(200);
        weightFull.setValue((int)currentUser.getWeight());
        weightFull.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });
        weightFull.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                full = newVal;
                currentWeight = Double.parseDouble(full + "." + half);
                setWeight.setTitle("Current Weight : " + currentWeight + " kg");
                currentUser.setWeight(currentWeight);
                tx.setText("BMI => "+(currentUser.getBMI()));
                speedometer.speedTo((float)currentUser.getBMI());
            }
        });

        weightDecimal.setMinValue(0);
        weightDecimal.setMaxValue(9);

        weightDecimal.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });
        weightDecimal.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                half = newVal;
                currentWeight = Double.parseDouble(full + "." + half);
                setWeight.setTitle("Current Weight : " + currentWeight);
                currentUser.setWeight(currentWeight);
                tx.setText("BMI => "+(currentUser.getBMI()));
                speedometer.speedTo((float)currentUser.getBMI());
            }
        });


        final NumberPicker heightFull = view.findViewById(R.id.heightFull);
        final ExpandableCardView setHeight = view.findViewById(R.id.setHeight);
        heightFull.setMinValue(100);
        heightFull.setMaxValue(250);
        heightFull.setValue((int)(currentUser.getHeight() * 100));
        setHeight.setTitle("Current Height : " +currentUser.getHeight() + " cm");
        heightFull.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });
        heightFull.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                setHeight.setTitle("Current Height : " + newVal + " cm");
                currentUser.setHeight(newVal);
                tx.setText("BMI => "+(currentUser.getBMI()));
                speedometer.speedTo((float)currentUser.getBMI());
            }
        });

        tx.setText("BMI => "+(currentUser.getBMI()));

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
    public void onDetach() {
        super.onDetach();
    }
}
