package com.tistory.cnux9.calculator4;

import java.util.ArrayList;

public class Calculator {
    ArrayList<Double> resultList;

    public Calculator() {
        resultList = new ArrayList<>();
    }

    public double calculate(double num1, double num2, char operator) {
        double result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                // 0으로 나누는 경우
                if (num2==0) {
                    System.out.println("0으로 나눌 수 없습니다.");
                } else {
                    result = num1 / num2;
                }
                break;
            default:
                System.out.println("입력이 적절하지 않습니다.");
        }
        resultList.add(result);
        return result;
    }

    public double getResult(int index) {
        return resultList.get(index);
    }

    public void setResult(int index, double value) {
        resultList.set(index, value);
    }

    public void removeFirstResult() {
        resultList.remove(0);
    }

    public double[] getResultsAtLeast(double min) {
        return resultList.stream().mapToDouble(Double::valueOf).filter(n -> n>min).toArray();
    }
}

