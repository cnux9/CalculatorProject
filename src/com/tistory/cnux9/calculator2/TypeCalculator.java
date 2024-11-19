package com.tistory.cnux9.calculator2;


// Calculator::calculateDouble / Calculator::calculateInt 를 인자로 받기 위한 함수형 인터페이스
@FunctionalInterface
interface TypeCalculator<T extends Number> {
    Double calculate(T num1, T num2, char operator);
}