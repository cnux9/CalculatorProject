package com.tistory.cnux9.calculator2;

@FunctionalInterface
interface TypeCalculator<T extends Number> {
    Double calculate(T num1, T num2, char operator);
}