package com.tistory.cnux9.calculator2;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    ArrayList<Integer> resultList;

    public Calculator() {
        resultList = new ArrayList<>();
    }

    public int calculate(int num1, int num2, char operator) {
        int result = 0;
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

    public int getResult(int index) {
        return resultList.get(index);
    }

    public void setResult(int index, int value) {
        resultList.set(index, value);
    }

    public void removeFirstResult() {
        resultList.remove(0);
    }
}

