package com.tistory.cnux9.calculator2;

@FunctionalInterface
interface Parser<T extends Number> {
    T parse(String text);
}