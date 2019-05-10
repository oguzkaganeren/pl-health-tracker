package com.example.healthtracker.core;

public class Person {
    private double weight;
    private double height;
    private double tension;
    private double bloodSugar;
    private double heartRate;
    private BMI bmi;


    public Person(double weight, double height) {
        this.setWeight(weight);
        this.setHeight(height);
        bmi = new BMI(weight,height);
    }

    public double getBMI(){
        return bmi.calculate();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getTension() {
        return tension;
    }

    public void setTension(double tension) {
        this.tension = tension;
    }

    public double getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(double bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
    }
}
