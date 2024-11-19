package com.tistory.cnux9.calculator2;

// Double::parseDouble / Integer::parseInt 를 인자로 받기 위한 함수형 인터페이스
@FunctionalInterface
interface Parser<T extends Number> {
    T parse(String text);
}