package com.example.tuf40653.variety;

/**
 * Created by tuf40653 on 5/13/2015.
 */
public class Calc {

    //Will calculatePay the total pay
    public static double calculatePay(double hrs, double rate) {
        return hrs*rate;
    }

    //Will calculate state income tax
    public static double calculateSIT(double income) {
        return income * .035;
    }

    //Will calculate federal income tax
    public static double calculateFIT(double income){
        return income * .15;
    }

    //Will calculate social security
    public static double calculateSS(double income){
        return income * .062;
    }

    //Will calculate Medicare
    public static double calculateMed(double income){
        return income * .029;
    }

}
