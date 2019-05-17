package com.deu.healthtracker.core;

public class EER {
    private Person newPerson;
    private double eer;
    public EER(Person newPerson)
    {
        this.newPerson = newPerson;
    }
    public double findPA()
    {
        if(newPerson.getAge() > 18)
        {
            switch (newPerson.getEer())
            {
                case "Sedentary":
                {
                    eer = 1;
                }break;
                case "Low Active":
                {
                    eer = 1.11;
                    if(!newPerson.isGender())
                    {
                        eer = 1.12;
                    }
                }break;
                case "Active":
                {
                    eer = 1.25;
                    if(!newPerson.isGender())
                    {
                        eer = 1.27;
                    }
                }break;
                case "Very Active":
                {
                    eer = 1.48;
                    if(!newPerson.isGender())
                    {
                        eer = 1.45;
                    }
                }break;
            }
        }
        else
        {
            eer = 0;
        }
        return eer;
    }
    public double getResult()
    {
        if(newPerson.isGender())
        {
            return 662 - (9.53 * newPerson.getAge()) + findPA()* (15.91 * newPerson.getWeight() + 539.6 * newPerson.getHeight());
        }
        return 354 - (6.91 * newPerson.getAge()) + findPA() * ( 9.36 * newPerson.getWeight() + 726 * newPerson.getHeight() );
    }
}
