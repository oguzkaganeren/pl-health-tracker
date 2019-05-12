package com.example.healthtracker.core;

public class Person {
    private double weight;
    private double height;
    private int age;
    private boolean gender;
    private int tension;//max 200 min 40
    private double bloodSugar;//max 25 min 0
    private int heartRate;//min 50 max 200
    private BMI bmi;

    public Person(){
        bmi = new BMI(0,0);
    }

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

    public int getTension() {
        return tension;
    }

    public void setTension(int tension) {
        this.tension = tension;
    }

    public double getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(double bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
