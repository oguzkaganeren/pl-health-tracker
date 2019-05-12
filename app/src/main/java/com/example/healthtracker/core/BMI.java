package com.example.healthtracker.core;

public class BMI {
    private double weight;
    private double height;
    private enum category{Underweight ,Normalweight,Overweight,Obesity, Error};
    private double result;
    private String color;
    public BMI(double weight, double height) {
        this.weight = weight;
        this.height = height;
        this.result = 0.0;
        this.color = "#DDDDDD";
    }

    public double calculate(){
        result = weight / (Math.pow(height, 2));
        return result;
    }

    public category getBMICategory(){
        if(result < 18.5) { color = "#66FFFF"; return category.Underweight; }
        else if(result >=18.5 && result <=24.9){ color = "#3366FF"; return category.Normalweight;}
        else if(result >=25 && result <= 29.9){ color = "#FF6633";return  category.Overweight;}
        else if(result >= 30) { color = "#FF0000"; return category.Obesity; }

        return category.Error;
    }

}
