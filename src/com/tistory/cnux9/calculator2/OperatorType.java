package com.tistory.cnux9.calculator2;

public enum OperatorType {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/');
    private final char symbol;

    // 변수 symbol 을 연산자 기호 char 형으로 초기화
    OperatorType(char operatorType) {
        this.symbol = operatorType;
    }

    public char getSymbol() {
        return this.symbol;
    }

    // 연산자에 해당하는 열거형 객체를 반환
    public static OperatorType fromSymbol(char c) {
        for (OperatorType operatorType : OperatorType.values()) {
            if (operatorType.getSymbol() == c) {
                return operatorType;
            }
        }
        return null;
    }
}
