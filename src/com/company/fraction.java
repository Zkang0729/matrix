package com.company;

public class fraction {
    int nominator;
    int denominator;

    public fraction(int nominator, int denominator) {
        this.nominator = nominator;
        this.denominator = denominator;
        if (denominator == 0) {
            System.out.println("The denominator of a fraction can not be zero!");
        }
    }

    public void printFraction() {
        if (denominator == 1) {
            System.out.println(nominator);
        } else {
            System.out.println(nominator + "/" + denominator);
        }
    }

    public fraction simplify() {
        for (int i = 1; i < Math.max(nominator, denominator); i++) {
            if (denominator % i == 0 && nominator % i == 0) {
                denominator /= i;
                nominator /= i;
            }
        }
        printFraction();
        return new fraction(nominator, denominator);
    }

    public fraction add(fraction fraction1, fraction fraction2) {
        int addNominator = fraction1.nominator * fraction2.denominator + fraction2.nominator * fraction2.denominator;
        int addDenominator = fraction1.denominator * fraction2.denominator;
        fraction add = new fraction(addNominator, addDenominator);
        add.simplify();
        return add;
    }

    public fraction subtract(fraction fraction1, fraction fraction2) {
        int addNominator = fraction1.nominator * fraction2.denominator - fraction2.nominator * fraction2.denominator;
        int addDenominator = fraction1.denominator * fraction2.denominator;
        fraction subtract = new fraction(addNominator, addDenominator);
        subtract.simplify();
        return subtract;
    }

    public fraction multiply(fraction fraction1, fraction fraction2) {
        int nominator = fraction1.nominator * fraction2.nominator;
        int denominator = fraction1.denominator * fraction2.denominator;
        fraction multiply = new fraction(nominator, denominator);
        multiply.simplify();
        return multiply;
    }

    public fraction divide(fraction fraction1, fraction fraction2) {
        fraction divide = multiply(fraction1, fraction2.toThePowerOf(-1));
        return divide;
    }

    public fraction toThePowerOf(int i) {
        if (i > 0) {
            for (int n = 0; n < i; n++) {
                nominator *= nominator;
                denominator *= denominator;
            }
            int n = nominator;
            int d = denominator;
            fraction power = new fraction(n, d);
            power.simplify();
            return power;
        } else if(i<0){
            for (int n = 0; n < Math.abs(i); n++) {
                nominator *= nominator;
                denominator *= denominator;
            }
            int n = denominator;
            int d = nominator;
            fraction power = new fraction(n, d);
            power.simplify();
            return power;
        }
        else{
            fraction power = new fraction(1,1);
            power.simplify();
            return power;
        }
    }
}