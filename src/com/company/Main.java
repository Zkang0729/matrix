package com.company;

public class Main {
    public static void main(String[] args) {
        double[][] example1 = new double[][]{
                {3,2,-2},
                {1,2,0},
                {1,0,-1}
        };
        double[][] example2 = new double[][]{
                {2.93},
                {2.22},
                {1.61},
                {1.79},
                {1.06}
        };
        double[][] vectorA = new double[][] {
                {1},
                {2},
                {2}
        };
        matrix vector = new matrix(vectorA);
        matrix matrix1 = new matrix(example1);
        matrix matrix2 = new matrix(example2);
        matrix1.rowReducedEchelon().printMatrix();
        matrix1.leastSquareSolution(vector).printMatrix();
    }
}