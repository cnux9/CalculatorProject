package com.tistory.cnux9.calculator2;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
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
            char operator = sc.nextLine().charAt(0);

            // 연산
            int result = calculator.calculate(num1, num2, operator);

            // 결과 출력
            System.out.println("결과: " + result);

            // 추가 기능
            boolean doAdditionalFeature = true;
            while(doAdditionalFeature) {
                System.out.print("계속 계산을 진행하시겠습니까?(확인: check, 수정: edit, 첫번째 결과 제거: remove, 종료: exit): ");
                String inputString = sc.nextLine();
                int index;
                switch (inputString) {
                    case "check":
                        System.out.print("몇 번째를 확인하실지 입력해주세요: ");
                        index = sc.nextInt();
                        sc.nextLine();
                        System.out.println(index + "번째 결과: " + calculator.getResult(index-1));
                        break;
                    case "edit":
                        System.out.print("몇 번째를 수정하실지 입력해주세요: ");
                        index = sc.nextInt();
                        sc.nextLine();
                        System.out.print("수정하실 값을 입력해주세요: ");
                        int value = sc.nextInt();
                        sc.nextLine();
                        calculator.setResult(index-1, value);
                        System.out.println("수정되었습니다.");
                        break;
                    case "remove":
                        calculator.removeFirstResult();
                        System.out.println("제거되었습니다.");
                        break;
                    case "exit":
                        isExit = true;
                        doAdditionalFeature = false;
                        break;
                    default:
                        doAdditionalFeature = false;
                }
            }
        }
    }
}

