## chapter4

- 스터디 당일에도 이야기했듯이 커링 패턴으로 의존성 주입을 할 수도 있다.
- 객체가 의존성을 들고 있는 형태를 커링 패턴으로 변경할 수 있다.
- 아래는 우리가 보통 사용하는 방식이다.
```kotlin
class Something(
    val aRepository: ARepository,
    val bAdapter: BAdapter 
) {
    fun getFromA(key: String): String {
        return aRepository.get(key)
    }
    
    fun getFromB(key: String): String {
        return bAdapter.get(key)
    }
}

val something = Something(ARepository(), BAdapter())
val a = something.getFromA(key)
val b = something.getFromB(key)
```

- 커링패턴을 사용하면 아래와 같이 변경할 수 있다.
```kotlin
fun getFromA(aRepository: ARepository): (key: String) -> String {
    return { key -> aRepository.get(key) }
}
fun getFromB(bAdapter: BAdapter): (key: String) -> String {
    return { key -> bAdapter.get(key) }
}

val getA = getFromA(ARepository())
val getB = getFromB(BRepository())
val a = getA(key)
val b = getB(key)
```
