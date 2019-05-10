package com.example.healthtracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthtracker.core.Person;


public class BmiFragment extends Fragment {

    private Person newPerson;

    public BmiFragment() {
        // Required empty public constructor

         newPerson = new Person(95, 1.67);
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
