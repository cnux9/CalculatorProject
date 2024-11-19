package com.tistory.cnux9.calculator2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^[0-9]*,[0-9]*$");
    private static final Scanner sc = new Scanner(System.in);
    private static boolean isDouble;

    public static void main(String[] args) {



        // 정수형 실수형 선택
        System.out.print("실수형 계산기를 원하시면 double을, 아니라면 double을 제외한 아무값이나 입력해주세요: ");
        String inputText0 = sc.nextLine();
        isDouble = inputText0.equals("double");
//        System.out.println("isDouble = " + isDouble);
        


        if (isDouble) {
            runCalculator(new Calculator<Double>(), Double::parseDouble, Calculator::calculateDouble);
        } else {
            runCalculator(new Calculator<Integer>(), Integer::parseInt, Calculator::calculateInt);
        }
    }

    // 메인 계산 루프
    private static <T extends Number> void runCalculator(Calculator<T> calculator, Parser<T> parser, TypeCalculator<T> typeCalculator) {
        boolean isExit = false;
        while(!isExit) {
            // 입력
            System.out.print("첫 번째 수를 입력해주세요: ");
            String inputText1 = sc.nextLine();

            System.out.print("두 번째 수를 입력해주세요: ");
            String inputText2 = sc.nextLine();

            System.out.print("사칙연산 기호를 입력해주세요: ");
            char operator = sc.nextLine().charAt(0);
//            OperatorType operatorType = OperatorType.;

            // 연산, 결과 출력
            try {
                T num1 = parser.parse(inputText1);
                T num2 = parser.parse(inputText2);
                double result = calculator.calculate(num1, num2, operator, typeCalculator);
                System.out.println("결과: " + result);
            } catch (ArithmeticException e) {
                System.out.println("0로 나눌 수 없습니다.");
            }

            // 추가 기능 루프
            boolean doAdditionalFeature = true;
            while(doAdditionalFeature) {
                System.out.print("계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit): ");
                String inputString = sc.nextLine();
                int index;
                double value;
                switch (inputString) {
                    case "check":
                        System.out.print("몇 번째 결과를 확인하실지 입력해주세요: ");
                        index = sc.nextInt();
                        sc.nextLine();
                        System.out.println(index + "번째 결과: " + calculator.getResult(index-1));
                        break;
                    case "filter":
                        System.out.print("입력한 값보다 큰 결과들이 출력됩니다. 값을 입력해주세요: ");
                        value = sc.nextDouble();
                        sc.nextLine();
                        double[] resultArray = calculator.getResultsAtLeast(value); // double or int?
                        String resultString = Arrays.stream(resultArray).mapToObj(Double::toString).collect(Collectors.joining(", ")); // 스트림을 통해
                        System.out.println("결과: " + resultString);
                        break;
                    case "edit":
                        System.out.print("몇 번째를 수정하실지 입력해주세요: ");
                        index = sc.nextInt();
                        sc.nextLine();
                        System.out.print("수정하실 값을 입력해주세요: ");
                        value = sc.nextDouble();
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

    private static void setIsDouble(String text) {
        isDouble = DOUBLE_PATTERN.matcher(text).matches();
    }

//    private static <T extends Number> double parse(String text) {
//        if (T instanceof Double) {
//            return Double.parseDouble(text);
//        }
//        return Integer.parseInt(text);
//    }
}

