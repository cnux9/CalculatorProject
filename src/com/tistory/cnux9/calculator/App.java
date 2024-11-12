package com.tistory.cnux9.calculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean isExit = false;
        while(!isExit) {
            // 입력
            System.out.print("첫 번째 수를 입력해주세요: ");
            int num1 = sc.nextInt();
            sc.nextLine();

            System.out.print("두 번째 수를 입력해주세요: ");
            int num2 = sc.nextInt();
            sc.nextLine();

            System.out.print("사칙연산 기호를 입력해주세요: ");
            String operator = sc.nextLine();

            // 연산, 결과 출력
            int result;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    System.out.println("결과: " + result);
                    break;
                case "-":
                    result = num1 - num2;
                    System.out.println("결과: " + result);
                    break;
                case "*":
                    result = num1 * num2;
                    System.out.println("결과: " + result);
                    break;
                case "/":
                    // 0으로 나누는 경우
                    if (num2==0) {
                        System.out.println("0으로 나눌 수 없습니다.");
                    } else {
                        result = num1 / num2;
                        System.out.println("결과: " + result);
                    }
                    break;
                default:
                    System.out.println("입력이 적절하지 않습니다.");
            }
            // 반복?
            System.out.print("계속 계산을 진행하시겠습니까?(나가려면 exit 입력): ");
            isExit = sc.nextLine().equals("exit");
        }
    }
}

