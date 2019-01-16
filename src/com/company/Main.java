package com.company;

public class Main {
    public static void main(String[] args) {
        double [][] example1 = new double[][] {
                {2,3,1},
                {1,2,3},
                {3,5,9}
        };
        matrix exampleMatrix = new matrix(example1);
        exampleMatrix.printDeterminant();
        double [][] example2 = new double[][] {
                {1,0,0},
                {0,0,1},
                {1,1,1}
        };
        matrix exampleMatrix2 = new matrix(example2);
        exampleMatrix2.printDeterminant();
    }
}