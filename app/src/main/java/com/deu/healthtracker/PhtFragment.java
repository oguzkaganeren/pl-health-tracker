package com.deu.healthtracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alespero.expandablecardview.ExpandableCardView;
import com.deu.healthtracker.core.Person;
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
        final Person person=new Person(this.getActivity());
        final EditText edit_text_sugar = (EditText)getView().findViewById(R.id.edit_text_blood_sugar);
        final EditText edit_text_heart = (EditText)getView().findViewById(R.id.edit_text_heart_rate);
        final EditText edit_text_tension = (EditText)getView().findViewById(R.id.edit_text_tension);
        final EditText edit_text_weight = (EditText)getView().findViewById(R.id.edit_text_weight);
        final FloatingActionButton btn_blood = (FloatingActionButton) getView().findViewById(R.id.btn_blood_sugar);
        final FloatingActionButton btn_heart = (FloatingActionButton) getView().findViewById(R.id.btn_heart_rate);
        final FloatingActionButton btn_tension = (FloatingActionButton) getView().findViewById(R.id.btn_tension);
        final FloatingActionButton btn_weight = (FloatingActionButton) getView().findViewById(R.id.btn_weight);
        final ExpandableCardView cardTension = getView().findViewById(R.id.tension_card);
        final ExpandableCardView cardHeart = getView().findViewById(R.id.heart_card);
        final ExpandableCardView cardSugar = getView().findViewById(R.id.sugar_card);
        final ExpandableCardView cardWeight = getView().findViewById(R.id.weight_card);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        final TextView warning=(TextView)getView().findViewById(R.id.txt_pht);

        btn_tension.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String content = edit_text_tension.getText().toString();

                if(person.pht.addTension(Integer.valueOf(content),warning)){
                    Toast.makeText(getActivity(), "Tension has added", Toast.LENGTH_LONG).show();
                    edit_text_tension.setText("");
                    cardTension.collapse();
                    edit_text_tension.clearFocus();
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }else{
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Add Tension")
                            .setMessage("Tension was not added, it should be between 40 and 400")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }


            }
        });
        btn_heart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String content = edit_text_heart.getText().toString();
                if(person.pht.addHeartRate(Integer.valueOf(content),warning)){
                    edit_text_heart.setText("");
                    edit_text_heart.clearFocus();
                    cardHeart.collapse();
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }else{
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Add heart rate")
                            .setMessage("Heart rate was not added, it should be between 50 and 300")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }


            }
        });
        btn_blood.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edit_text_sugar.getText().toString();
                if(person.pht.addBloodSugar(Double.valueOf(content),warning)){
                    edit_text_sugar.setText("");
                    edit_text_sugar.clearFocus();
                    cardSugar.collapse();
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }else{
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Add blood sugar")
                            .setMessage("Blood sugar was not added, it should be between 0 and 25")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
        });
        btn_weight.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String content = edit_text_weight.getText().toString();
                if(person.pht.addWeight(Double.valueOf(content),warning)){
                    edit_text_weight.setText("");
                    edit_text_weight.setText("");
                    cardWeight.collapse();
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }else{
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Add weight")
                            .setMessage("Weight was not added, it should be between 30 and 450")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
        });


    }


}
