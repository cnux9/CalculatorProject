package com.tistory.cnux9.calculator2;

import java.util.ArrayList;

public class Calculator<T extends Number> {
    ArrayList<Double> resultList;

    public Calculator() {
        resultList = new ArrayList<>();
    }

    // 타입 유효성 검사 후, double/int형에 알맞은 메소드로 결과값을 구한 뒤, 결과 리스트에 값을 저장하고나서 값을 반환한다.
    public double calculate(T num1, T num2, char operator, TypeCalculator<T> typeCalculator) {
        // double이나 int형이 아니라면 적절하지 않은 인자 예외 발생
        if(!isAllowedType(num1) || !isAllowedType(num2)) {
            throw new IllegalArgumentException("정수나 실수만 가능");
        }
        // double 혹은 int에 해당하는 계산 메소드에서 연산 수행 후 double 반환
        double result = typeCalculator.calculate(num1, num2, operator);
        resultList.add(result);
        return result;
    }

    // index 값을 받아 저장된 계산 결과값 반환
    public double getResult(int index) {
        return resultList.get(index);
    }

    // 특정 index 위치의 값을 바꿈
    public void setResult(int index, double value) {
        resultList.set(index, value);
    }

    // 결과 리스트의 첫 번째 항목 제거
    public void removeFirstResult() {
        resultList.remove(0);
    }

    // 필터링된 결과를 조화하기 위한 메소드(스트림 활용)
    public double[] getResultsAtLeast(double min) {
        return resultList.stream().mapToDouble(Double::valueOf).filter(n -> n>min).toArray();
    }

    // 타입 유효성 검사
    private boolean isAllowedType(T num) {
        return num instanceof Double || num instanceof Integer;
    }

    // int형에 대응하는 연산 메소드
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

    // double형에 대응하는 연산 메소드
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

