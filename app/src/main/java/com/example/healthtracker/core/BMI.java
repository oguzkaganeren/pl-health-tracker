package com.example.healthtracker.core;

import android.graphics.Color;

public class BMI {
    private double result;
    private int color;
    private Person currentPerson;

    public BMI(Person currentPerson) {
        this.result = 0.0;
        setColor(Color.RED);
        this.currentPerson = currentPerson;
    }

    public double calculate(){
        result = currentPerson.getWeight()/ (Math.pow(currentPerson.getHeight(), 2));
        return result;
    }

    public String getBMICategory(){
        if(result < 18.5) { setColor(Color.GREEN); return "Underweight"; }
        else if(result >=18.5 && result <=24.9){ setColor(Color.CYAN); return "Normal Weight"; }
        else if(result >=25 && result <= 29.9){ setColor(Color.BLUE); return  "Overweight"; }
        else if(result >= 30) { setColor(Color.RED); return "Obesity"; }

        return "Error";
    }

    private void setColor(int color){ this.color = color;}

    public int getColor() { return this.color; }
}
