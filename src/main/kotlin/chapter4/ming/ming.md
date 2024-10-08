### 고차 함수란?
명령형 언어에서 상태를 변경하거나 반복문을 사용한다면 함수형 언어에서는 고차 함수를 사용한다. 매개변수나 반환값에 함수를 사용하면 고차함수이다.

### 부분 함수란 무엇인가? 왜 필요한가?
모든 입력이 정의되지 않은 함수. 정의되지 않은 입력에 대해서는 오류가 발생할 수 있다.
```kotlin
// 부분 함수
fun sayNumber(n: Int): String = when(n) {
	1 -> "say 1"
	2 -> "say 2"
	else -> throw RunTimeException()
}
```
어디에 쓰이는가? 가급적이면 쓰이지 않는 것이 좋겠지만 오류가 발생하는 상황에서 예외 처리할때 유용하게 사용된다. 예를 들어 리스트의 첫 번째 요소를 반환하는 head 함수는 빈 리스트에 대해 정의되지 않는 부분 함수이다. 이를 통해 코드의 예외 상황을 인지하고 처리할 수 있게 된다.
### 커링 함수란 무엇인가? 왜 필요한가?
커링은 Haskell Curry라는 수학자의 이름을 따서 명명. 하스켈이라는 언어도 저 사람에서 따온 것.
여러 개의 매개변수를 받는 함수를 분리하여, 단일 매개변수를 받는 부분 적용함수를 체이닝하는 방법을 커링이라고 한다.
함수의 재사용성을 높이고 함수의 실행시점을 늦출 수 있다.

### 합성 함수와 포인트 프리 스타일 프로그래밍
합성 함수란 함수를 매개변수로 받고, 함수를 반환할 수 있는 고차함수를 이용해 두 개의 함수를 결합하는 것을 의미한다. 수학의 합성 함수와 동일한 개념이다.
코틀린에서는 infix를 이용해 매개변수를 양쪽으로 받도록 할 수 있다.
```kotlin
infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (F) -> R {
	return { gInput: G -> this(g(gInput)) }
}

val addThree = { i: Int -> i + 3 }
val twice = { i: Int -> i * 2 }
val composedFunc = addThree compose twice // 와우!
val result = compoedFunc(3) // 18 -> addThree(twice(3))
```
이처럼 합성 함수를 이용해 매개 변수나 타입 선언 없이 함수를 만드는 방식을 포인트 프리 프로그래밍이라고 한다. 이 방식은 함수 체이닝이 많아졌을때 간결하게 표현할 수 있는 장점이 있다. 
