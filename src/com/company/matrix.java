package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;

public class matrix {
    double[][] matrix;

    public matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getArray() {
        return this.matrix;
    }

    public int getRow() {
        int i = matrix.length;
        return i;
    }

    public int getCol() {
        int j = matrix[0].length;
        return j;
    }

    public double getNumber(int i, int j) {
        double number = matrix[i][j];
        return number;
    }

    public com.company.matrix scalarMultiply(float scalar) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] *= scalar;
            }
        }
        com.company.matrix scalarMultiply = new com.company.matrix(matrix);
        return scalarMultiply;
    }

    public static com.company.matrix combineMatrix(@NotNull com.company.matrix matrix1, @NotNull com.company.matrix matrix2) {
        double[][] combineMatrix = new double[matrix1.getRow()][matrix1.getCol() + matrix2.getCol()];
        if (matrix1.getRow() == matrix2.getRow()) {
            for (int i = 0; i < matrix1.getRow(); i++) {
                for (int j = 0; j < matrix1.getCol(); j++) {
                    combineMatrix[i][j] = matrix1.getNumber(i, j);
                }
            }
            for (int i = 0; i < matrix1.getRow(); i++) {
                for (int j = matrix1.getCol(); j < matrix1.getCol() + matrix2.getCol(); j++) {
                    combineMatrix[i][j] = matrix2.getNumber(i, j - matrix1.getCol());
                }
            }
        } else {
            System.out.println("This calculation is not valid.");
        }
        com.company.matrix combine = new com.company.matrix(combineMatrix);
        return combine;
    }

    public void setNumber(int i, int j, float number) {
        matrix[i][j] = number;
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j != matrix[0].length - 1) {
                    System.out.print(matrix[i][j] + " ");
                } else {
                    System.out.print(matrix[i][j]);
                    System.out.println();
                }
            }
        }
    }

    public com.company.matrix add(com.company.matrix matrix1, com.company.matrix matrix2) {
        double[][] addMatrix = new double[matrix1.getRow()][matrix1.getCol()];
        if ((matrix1.getRow() == matrix2.getRow() && (matrix1.getCol() == matrix2.getCol()))) {
            for (int i = 0; i < addMatrix.length; i++) {
                for (int j = 0; j < addMatrix[0].length; j++) {
                    addMatrix[i][j] = matrix1.getNumber(i, j) + matrix2.getNumber(i, j);
                }
            }
        } else {
            System.out.println("The calculation is not valid!");
        }
        com.company.matrix sumMatrix = new com.company.matrix(addMatrix);
        return sumMatrix;
    }

    public static com.company.matrix multiply(@NotNull com.company.matrix matrix1, @NotNull com.company.matrix matrix2) {
        double[][] multiplyMatrix = new double[matrix1.getRow()][matrix2.getCol()];
        if (matrix1.getCol() == matrix2.getRow()) {
            for (int i = 0; i < multiplyMatrix.length; i++) {
                for (int j = 0; j < multiplyMatrix[0].length; j++) {
                    double sum = 0;
                    for (int k = 0; k < matrix1.getCol(); k++) {
                        for (int l = 0; l < matrix2.getRow(); l++) {
                            sum += matrix1.getNumber(i, k) * matrix2.getNumber(l, j);
                        }
                    }
                    multiplyMatrix[i][j] = sum;
                }
            }
        } else {
            System.out.println("The calculation is not valid!");
        }
        com.company.matrix sumMatrix = new com.company.matrix(multiplyMatrix);
        return sumMatrix;
    }

    public com.company.matrix transpose() {
        double[][] transposeMatrix = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposeMatrix[j][i] = matrix[i][j];
            }
        }
        com.company.matrix resultMatrix = new com.company.matrix(transposeMatrix);
        return resultMatrix;
    }

    public boolean allZero(int row) {
        boolean allZero = true;
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[row][j] != 0) {
                allZero = false;
                break;
            }
        }
        return allZero;
    }

    public void swapRows(int i, int j) {
        double[] swap = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = swap;
    }

    public com.company.matrix rowEchelon() {
        if (matrix.length <= matrix[0].length) {
            for (int k = 0; k < matrix.length; k++) {
                int max = k;
                for (int i = k; i < matrix.length; i++) {
                    if (Math.abs(matrix[i][k]) > Math.abs(matrix[max][k])) {
                        max = i;
                    }
                }
                swapRows(k, max);
                for (int i = k; i < matrix.length - 1; i++) {
                    if (matrix[k][k] == 0) {
                        matrix[i + 1][k] = 0;
                    } else {
                        double factor = matrix[i + 1][k] / matrix[k][k];
                        for (int j = k; j < matrix[0].length; j++) {
                            matrix[i + 1][j] -= factor * matrix[k][j];
                        }
                    }
                }
            }
        } else {
            for (int k = 0; k < matrix[0].length; k++) {
                int max = k;
                for (int i = k; i < matrix.length; i++) {
                    if (Math.abs(matrix[i][k]) > Math.abs(matrix[max][k])) {
                        max = i;
                    }
                }
                swapRows(k, max);
                for (int i = k; i < matrix.length - 1; i++) {
                    if (matrix[k][k] == 0) {
                        matrix[i + 1][k] = 0;
                    } else {
                        double factor = matrix[i + 1][k] / matrix[k][k];
                        for (int j = k; j < matrix[0].length; j++) {
                            matrix[i + 1][j] -= factor * matrix[k][j];
                        }
                    }
                }
            }
        }
        int[] order = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            order[i] = firstNonZero(i);
        }
        int[] unsortedOrder = new int[order.length];
        for (int i = 0; i < unsortedOrder.length; i++) {
            unsortedOrder[i] = order[i];
        }
        Arrays.sort(order);
        double[][] toReduced = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int index = 0;
            for (int k = 0; k < unsortedOrder.length; k++) {
                if (order[matrix.length - 1 - i] == unsortedOrder[k]) {
                    index = k;
                    break;
                }
            }
            toReduced[i] = matrix[index];
        }
        com.company.matrix rowEchelon = new com.company.matrix(matrix);
        int time = 1;
        while (rowEchelon.firstNonZero(0) == -1 && time <= matrix.length) {
            for (int i = 0; i < matrix.length - 1; i++) {
                rowEchelon.swapRows(i, i + 1);
            }
            time++;
        }
        return rowEchelon;
    }

    public int firstNonZero(int i) {
        for (int j = 0; j < matrix[i].length; j++) {
            if (matrix[i][j] != 0) {
                return j;
            }
        }
        return -1;
    }

    public com.company.matrix findCofactor(int row, int col) {
        double[][] removeColumn = new double[matrix.length][matrix[0].length - 1];
        double[][] removeRow = new double[matrix.length - 1][matrix[0].length - 1];
        if (matrix.length == matrix[0].length) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length - 1; j++) {
                    if (j < col) {
                        removeColumn[i][j] = matrix[i][j];
                    } else {
                        removeColumn[i][j] = matrix[i][j + 1];
                    }
                }
            }
            for (int i = 0; i < matrix.length - 1; i++) {
                for (int j = 0; j < matrix[0].length - 1; j++) {
                    if (i < row) {
                        removeRow[i][j] = removeColumn[i][j];
                    } else {
                        removeRow[i][j] = removeColumn[i + 1][j];
                    }
                }
            }
        } else {
            System.out.println("This calculation is not valid!");
        }
        com.company.matrix cofactor = new com.company.matrix(removeRow);
        return cofactor;
    }

    public double findDeterminant() {
        double determinant = 0;
        if (matrix.length == matrix[0].length) {
            if (matrix.length > 2) {
                for (int j = 0; j < matrix[0].length; j++) {
                    determinant += Math.pow(-1, j) * matrix[0][j] * (findCofactor(0, j).findDeterminant());
                }
            } else {
                determinant += matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
            }
        } else {
            System.out.println("This is not a valid calculation");
        }
        return determinant;
    }

    public void printDeterminant() {
        System.out.println("The determinant of the matrix is: " + findDeterminant());
    }

    public com.company.matrix rowReducedEchelon() {
        this.rowEchelon();
        for (int i = 0; i < matrix.length; i++) {
            if (allZero(i)) {
                continue;
            } else {
                double coefficient = matrix[i][firstNonZero(i)];
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] /= coefficient;
                }
            }
        }
        for (int i = matrix.length - 1; i > 0; i--) {
            if (allZero(i)) {
                continue;
            } else {
                for (int k = i; k > 0; k--) {
                    double coefficient = matrix[k - 1][firstNonZero(i)];
                    for (int j = 0; j < matrix[0].length; j++) {
                        matrix[k - 1][j] -= matrix[i][j] * coefficient;
                    }
                }
            }
        }
        int[] order = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            order[i] = firstNonZero(i);
        }
        int[] unsortedOrder = new int[order.length];
        for (int i = 0; i < unsortedOrder.length; i++) {
            unsortedOrder[i] = order[i];
        }
        Arrays.sort(order);
        double[][] toReduced = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int index = 0;
            for (int k = 0; k < unsortedOrder.length; k++) {
                if (order[i] == unsortedOrder[k]) {
                    index = k;
                    break;
                }
            }
            toReduced[i] = matrix[index];
        }
        com.company.matrix rowReducedEchelonForm = new com.company.matrix(toReduced);
        int time = 1;
        while (rowReducedEchelonForm.firstNonZero(0) == -1 && time <= matrix.length) {
            for (int i = 0; i < matrix.length - 1; i++) {
                rowReducedEchelonForm.swapRows(i, i + 1);
            }
            time++;
        }
        return rowReducedEchelonForm;
    }

    public com.company.matrix findInverse() {
        com.company.matrix matrix = new com.company.matrix(this.matrix);
        System.out.println("What's the dimension of the matrix you are going to compute?");
        Scanner dimension = new Scanner(System.in);
        int dim = dimension.nextInt();
        double[][] inverseMatrix = new double[dim][dim];
        if (matrix.findDeterminant() != 0) {
            double[][] identityMatrix = new double[dim][dim];
            for (int i = 0; i < dim; i++) {
                identityMatrix[i][i] = 1;
            }
            com.company.matrix identity = new com.company.matrix(identityMatrix);
            com.company.matrix toInverse = combineMatrix(matrix, identity).rowReducedEchelon();
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    inverseMatrix[i][j] = toInverse.getNumber(i, j + dim);
                }
            }
        } else {
            System.out.println("The matrix is singular!");
            return null;
        }
        com.company.matrix inverse = new com.company.matrix(inverseMatrix);
        inverse.printMatrix();
        return inverse;
    }

    public matrix leastSquareSolution(matrix vectorB) {
        double[][] solutionVector = new double[matrix.length][1];
        if (matrix.length ==  vectorB.getRow()) {
            matrix originMatrix = new matrix(matrix);
            matrix solveMatrix = combineMatrix(multiply(originMatrix.transpose(), originMatrix), multiply(originMatrix.transpose(), vectorB));
            matrix rowReducedEchelonForm = solveMatrix.rowReducedEchelon();
            for (int i = 0; i < matrix[0].length; i++) {
                solutionVector[i][0] = rowReducedEchelonForm.getNumber(i, solveMatrix.getCol() - 1);
            }
        } else {
            System.out.println("This calculation is not valid");
        }
        matrix solution = new matrix(solutionVector);
        return solution;
    }

    public matrix solve(matrix vectorB) {
        double[][] solutionVector = new double[matrix.length][1];
        if (matrix.length == vectorB.getRow()){
            matrix originMatrix = new matrix(matrix);
            matrix solveMatrix = combineMatrix(originMatrix, vectorB);
            matrix rowReducedEchelonForm = solveMatrix.rowReducedEchelon();
            for (int i = 0; i < matrix[0].length; i++){
                solutionVector[i][0] = rowReducedEchelonForm.getNumber(i, solveMatrix.getCol()-1);
            }
        } else {
            System.out.println("This calculation is not valid");
        }
        matrix solution = new matrix(solutionVector);
        return solution;
    }
}