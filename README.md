
# Calculator Project

---

# 📌 계산기 예제


입력받은 두 숫자로 사칙연산을 수행할 수 있는 자바 프로그램입니다.
대략적인 실행 과정은 아래와 같습니다.


1. 입력값이 double형인지 int형인지 선택합니다.
2. 두 숫자와 연산자를 입력합니다.
3. 연산 결과를 출력합니다
4. 이전 연산 결과를 확인/수정하거나 계속 연산을 진행합니다.
5. exit을 입력하면 종료합니다.

---

# 📌 실행 결과 예시
```
실수형 계산기를 원하시면 double을, 아니라면 double을 제외한 아무값이나 입력해주세요: double
실수형 계산기입니다!

첫 번째 수를 입력해주세요: 7.04
두 번째 수를 입력해주세요: 2.25
사칙연산 기호를 입력해주세요: /
결과: 3.128888888888889

계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit):

첫 번째 수를 입력해주세요: 2
두 번째 수를 입력해주세요: 1
사칙연산 기호를 입력해주세요: -
결과: 1.0

계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit):

첫 번째 수를 입력해주세요: 18.18
두 번째 수를 입력해주세요: 0.82
사칙연산 기호를 입력해주세요: +
결과: 19.0

계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit):check
몇 번째 결과를 확인하실지 입력해주세요: 3
3번째 결과: 19.0

계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit):filter
입력한 값보다 큰 결과들이 출력됩니다. 값을 입력해주세요: 2
결과: 3.128888888888889, 19.0

계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit):edit
몇 번째를 수정하실지 입력해주세요: 2
수정하실 값을 입력해주세요: 1
수정되었습니다.

계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit):filter
입력한 값보다 큰 결과들이 출력됩니다. 값을 입력해주세요: 1
결과: 3.128888888888889, 19.0

계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit):filter
입력한 값보다 큰 결과들이 출력됩니다. 값을 입력해주세요: 3
결과: 3.128888888888889, 19.0

계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit):remove
제거되었습니다.

계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit):filter
입력한 값보다 큰 결과들이 출력됩니다. 값을 입력해주세요: 3
결과: 19.0

계속 계산을 진행하시겠습니까?(확인: check, 필터링: filter, 수정: edit, 첫 번째 결과 제거: remove, 종료: exit):exit


Process finished with exit code 0
```

---

# 📌 트러블 슈팅
## 제네릭 기능의 한계

제네릭 기능을 처음 배웠을 때는 **타입에 유연하게 작동하는 코드를 작성할 수 있는 강력한 도구**라고 생각했습니다. 하지만 구현을 진행하면서 **제네릭의 여러 제약**이 있다는 걸 알게 되었습니다.

`int`형과 `double`형을 모두 처리할 수 있는 `Calculator` 클래스를 만들며 겪은 주요 제약은 다음과 같습니다:

### 1. Parse 메소드 문제
`Scanner`를 통해 입력된 텍스트를 숫자로 변환할 때,  
`Integer.parseInt()` 또는 `Double.parseDouble()` 메소드를 사용해야 했습니다.  
하지만, **제네릭 타입 매개변수로는 이를 단순하게 만들 수 없었습니다.**

### 2. 산술 연산자를 사용할 수 없는 문제
`Double`형 또는 `Integer`형으로 쓰일 제네릭 타입 매개변수 `T`가  
`Number`를 상속받도록 제한해도 **`+`, `-`, `*`, `/` 같은 산술 연산자를 사용할 수 없었습니다.**

---

## 해결 방법

이 문제를 해결하기 위해 다음 방법들을 고민했습니다:

### 1. `instanceof` 사용
메소드에서 `instanceof`를 이용해 `Double`형과 `Integer`형을 구분하고,  
조건문으로 두 경우를 나눠서 처리하는 방식입니다.

### 2. Boolean `isDouble` 사용
`isDouble`이라는 boolean형 전역변수를 만들어,  
`true`일 경우 `double` 계산, `false`일 경우 `int` 계산을 수행하는 방식입니다.

### 3. 함수형 인터페이스 사용
`Parser`라는 함수형 인터페이스를 만들고,  
`Double::parseDouble`, `Integer::parseInt`와 같은 메서드를 인자로 전달합니다.  
산술 연산이 필요한 부분도 따로 메소드를 추출하여 메서드 참조를 통해 처리하도록 만듭니다.

```
if (isDouble) {
    runCalculator(new Calculator<Double>(), Double::parseDouble, Calculator::calculateDouble);
} else {
    runCalculator(new Calculator<Integer>(), Integer::parseInt, Calculator::calculateInt);
}
```

---

## 결론

위 방법들을 비교한 결과:

- **1번과 2번 (`instanceof`, `isDouble`)**  
  - 조건문이 메인 루프에서 반복적으로 검사되기 때문에 약간 비효율적이라고 생각했습니다.
  - 조건문이 여러 곳에 흩어져 있어 코드 유지보수성과 확장성이 부족합니다.

- **3번 (함수형 인터페이스)**  
  - 메서드 참조를 활용하여 코드가 간결해졌습니다.
  - 새로운 타입이 추가될 상황에서 확장성이 뛰어납니다.

따라서, **`Parser`와 `TypeCalculator`를 구현하여**  
`main` 메소드에서 `double` 또는 `int`를 결정하고,  
적절한 메소드를 `runCalculator`에 전달하여 계산이 이루어지도록 만들었습니다.
```
@FunctionalInterface
interface TypeCalculator<T extends Number> {
    Double calculate(T num1, T num2, char operator);
}
```

---

## 제네릭의 목적

**제네릭은 안 되는 걸 되게 하는 도구가 아니라**  
오히려 **타입을 제한하고, 재사용 가능한 코드를 작성**하는 데 목적이 있다는 것을 알게 되었습니다.

---

# 📌 예외 처리

### 1. 0으로 나누기
0으로 나누는 경우 `ArithmeticException`을 발생시키고,  
안내 메시지를 출력한 후, 프로그램이 정상적으로 이어지도록 처리했습니다.

### 2. 적절하지 않은 제네릭 타입 매개변수
`calculate` 메소드는 내부적으로 `double`과 `int`만 처리할 수 있습니다.  
현재 프로젝트에서는 문제가 없지만, 다른 타입이 전달되면 문제가 발생할 수 있습니다.  

이를 방지하기 위해, **유효성 검사를 통해 적절하지 않은 타입일 경우**  
`IllegalArgumentException`을 발생시켜 타입 안정성을 보장했습니다.

---

# 📌 구성

- **`App.main()`**  
  - **`double`형 / `int`형 결정**

- **`runCalculator()`**  
  - **메인 계산 루프**
    - **`Parser.parse()`**: 입력받은 텍스트를 숫자형으로 변환
    - **`Calculator.calculate()`**: 사칙연산 수행
      - **`Calculator.isAllowedType()`**: 타입 유효성 검사
      - **`TypeCalculator.calculate()`**:  
        - **`Calculator.calculateInt()`**: 정수형 계산
        - **`Calculator.calculateDouble()`**: 실수형 계산

  - **추가 기능**
    - **`Calculator.getResult()`**: 결과 리스트 조회
    - **`Calculator.setResult()`**: 결과 리스트 수정
    - **`Calculator.removeFirstResult()`**: 첫 번째 결과값 제거
    - **`Calculator.getResultsAtLeast()`**: 필터링된 결과값 나열

---

