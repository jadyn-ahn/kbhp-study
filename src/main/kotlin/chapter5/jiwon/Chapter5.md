### chapter 5

kotlin sequence 내부 어떻게 동작할까?
```kotlin
@kotlin.internal.InlineOnly
public inline fun <T> Sequence(crossinline iterator: () -> Iterator<T>): Sequence<T> = object : Sequence<T> {
    override fun iterator(): Iterator<T> = iterator()
}

public fun <T> Sequence<T>.first(): T {
    val iterator = iterator()
    if (!iterator.hasNext())
        throw NoSuchElementException("Sequence is empty.")
    return iterator.next()
}

@SinceKotlin("1.4")
public fun Sequence<Float>.minOrNull(): Float? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var min = iterator.next()
    while (iterator.hasNext()) {
        val e = iterator.next()
        min = minOf(min, e)
    }
    return min
}

```
