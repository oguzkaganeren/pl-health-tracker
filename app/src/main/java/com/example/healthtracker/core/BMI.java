package com.example.healthtracker.core;

public class BMI {
    private double result;
    private String color;
    private Person currentPerson;

    public BMI(Person currentPerson) {
        this.result = 0.0;
        this.color = "#DDDDDD";
        this.currentPerson = currentPerson;
    }

    public double calculate(){
        result = currentPerson.getWeight()/ (Math.pow(currentPerson.getHeight(), 2));
        return result;
    }

    public String getBMICategory(){
        if(result < 18.5) { setColor("#66FFFF"); return "Underweight"; }
        else if(result >=18.5 && result <=24.9){ setColor("#3366FF"); return "Normal Weight"; }
        else if(result >=25 && result <= 29.9){ setColor("#FF6633"); return  "Overweight"; }
        else if(result >= 30) { setColor("#FF0000"); return "Obesity"; }

        return "Error";
    }

    private void setColor(String color){ this.color = color;}

    public String getColor() { return this.color; }
}
