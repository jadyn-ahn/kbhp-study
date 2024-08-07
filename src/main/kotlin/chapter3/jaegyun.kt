package chapter3

fun<T> List<T>.head(): T = when (this.size) {
    0 -> throw RuntimeException("no element in list")
    else -> this[0]
}

fun<T> List<T>.tail(): List<T> = when (this.size) {
    0 -> listOf()
    else -> this.subList(1, this.size)
}

// 3-2
fun power(x: Double, n: Int): Double =
    when (n) {
        0 -> 1.0
        else -> x * power(x, n - 1)
    }

// 3-3
fun factorial(n: Int): Int =
    when (n) {
        0 -> 1
        else -> n * factorial(n - 1)
    }

// 3-4
fun toBinary(n: Int): String =
    when (n) {
        0 -> "0"
        1 -> "1"
        else -> toBinary(n / 2) + n % 2
    }

// 3-5
fun replicate(n: Int, element: Int): List<Int> =
    when (n) {
        0 -> emptyList<Int>()
        else -> listOf(element) + replicate(n - 1, element)
    }

// 3-6
fun elem(num: Int, list: List<Int>): Boolean =
    when (list.size) {
        0 -> false
        1 -> list.head() == num
        else -> list.head() == num || elem(num, list.tail())
    }
