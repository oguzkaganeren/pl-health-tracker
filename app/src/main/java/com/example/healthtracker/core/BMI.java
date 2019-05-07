package com.example.healthtracker.core;

public class BMI {
    private double weight;
    private double height;

    public BMI(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public double calculate(){
        return weight / (Math.pow(height, 2));
    }
}
