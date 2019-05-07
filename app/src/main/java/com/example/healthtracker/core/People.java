package com.example.healthtracker.core;

public class People {
    private double weight;
    private double height;
    private BMI bmi;

    public People(double weight, double height) {
        this.weight = weight;
        this.height = height;
        bmi = new BMI(weight,height);
    }

    public double getBMI(){
        return bmi.calculate();
    }
}
