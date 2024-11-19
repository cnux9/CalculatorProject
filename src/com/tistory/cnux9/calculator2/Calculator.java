package com.tistory.cnux9.calculator2;

import java.util.ArrayList;

public class Calculator<T extends Number> {
    ArrayList<Double> resultList;

    public Calculator() {
        resultList = new ArrayList<>();
    }

    public double calculate(T num1, T num2, char operator, TypeCalculator<T> typeCalculator) {
        if(!isAllowedType(num1) || !isAllowedType(num2)) {
            throw new IllegalArgumentException("정수나 실수만 가능");
        }

        // 매개변수의 타입 검사 후 Double or Integer 조건 분기
        double result = typeCalculator.calculate(num1, num2, operator);

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

    // 필터링된 결과를 조화하기 위한 메소드
    public double[] getResultsAtLeast(double min) {
        return resultList.stream().mapToDouble(Double::valueOf).filter(n -> n>min).toArray();
    }

    // 타입 유효성 검사
    private boolean isAllowedType(T num) {
        return num instanceof Double || num instanceof Integer;
    }

    public static double calculateInt(int num1, int num2, char operator) {
        return switch (OperatorType.fromSymbol(operator)) {
            case ADD -> num1 + num2;
            case SUBTRACT -> num1 - num2;
            case MULTIPLY -> num1 * num2;
            case DIVIDE -> {
                // 0으로 나누는 경우
                if (num2 == 0) {
                    throw new ArithmeticException("/ by zero");
                }
                yield num1 / num2;
            }
        };
    }

    public static double calculateDouble(double num1, double num2, char operator) {
        return switch (OperatorType.fromSymbol(operator)) {
            case ADD -> num1 + num2;
            case SUBTRACT -> num1 - num2;
            case MULTIPLY -> num1 * num2;
            case DIVIDE -> {
                // 0으로 나누는 경우
                if (num2 == 0) {
                    throw new ArithmeticException("/ by zero");
                }
                yield num1 / num2;
            }
        };
    }
}

