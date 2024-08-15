package chapter3.jiwon

import java.math.BigDecimal
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

fun <T> List<T>.head(): T = when (this.size) {
    0 -> throw RuntimeException("no element in list")
    else -> this[0]
}

fun <T> List<T>.tail(): List<T> = when (this.size) {
    0 -> listOf()
    else -> this.subList(1, this.size)
}

// 7
fun takeSequence(n: Int, sequence: Sequence<Int>): List<Int> = when {
    n <= 0 -> listOf()
    sequence.none() -> listOf()
    else -> listOf(sequence.first()) + takeSequence(n - 1, sequence.drop(1))
}

// 3-8
fun quickSort(list: List<Int>): List<Int> = when {
    list.isEmpty() -> emptyList<Int>()
    else -> {
        val pivot = list.head()
        val rest = list.tail()
        val (smaller, bigger) = rest.partition { it < pivot }
        quickSort(smaller) + pivot + quickSort(bigger)
    }
}

// 3-9
fun gcd(m: Int, n: Int): Int {
    val bigger = max(m, n)
    val smaller = min(m, n)

    if (smaller == 0) {
        return 1
    }

    val rem = bigger % smaller
    return if (rem == 0) {
        smaller
    } else {
        gcd(smaller, rem)
    }
}

// 3 - 10
fun factorialMemoization(n: Int): Int {
    val memo = Array(n + 1) { -1 }

    fun factorial(num: Int): Int = when {
        num <= 1 -> 1
        memo[num] != -1 -> memo[num]
        else -> {
            memo[num] = num * factorial(num - 1)
            memo[num]
        }
    }

    return factorial(n)
}

// 3 - 11 / 12
fun factorialFP(n: Int): Int = factorialFP(n, 1)

tailrec fun factorialFP(n: Int, medium: Int): Int = when (n) {
    0 -> medium
    1 -> medium
    else -> factorialFP(n - 1, n * medium)
}

// 3 - 13
fun power(x: Double, n: Int): Double = power(x, n, 1.0)

tailrec fun power(x: Double, n: Int, acc: Double): Double = when {
    x == 0.0 -> if (n == 0) 1.0 else 0.0
    n == 0 -> acc
    else -> power(x, n - 1, acc * x)
}

// 3 - 14
fun toBinary(n: Int): String = toBinary(n, "")

tailrec fun toBinary(n: Int, acc: String): String = when {
    n <= 1 -> n.toString() + acc
    else -> toBinary(n / 2, (n % 2).toString() + acc)
}

// 3 - 15
fun replicate(n: Int, element: Int) = replicate(n, element, emptyList())

tailrec fun replicate(n: Int, element: Int, acc: List<Int>): List<Int> = when {
    n == 0 -> acc
    else -> replicate(n - 1, element, acc + listOf(element))
}

// 3 - 16
fun elem(num: Int, list: List<Int>) = elem(num, list, false)

tailrec fun elem(num: Int, list: List<Int>, acc: Boolean): Boolean = when {
    list.isEmpty() -> acc
    acc -> true
    else -> elem(num, list.tail(), list.head() == num)
}

// 3- 17
fun root(n: Double): Double = when {
    n < 1 -> n
    else -> divider(sqrt(n))
}

fun divider(n: Double): Double = when {
    n < 1 -> n
    else -> root(n / 2)
}

// 3 - 18
sealed class Bounce<A>
data class Done<A>(val result: A) : Bounce<A>()
data class More<A>(val thunk: () -> Bounce<A>) : Bounce<A>()

tailrec fun <A> trampoline(bounce: Bounce<A>): A = when (bounce) {
    is Done -> bounce.result
    is More -> trampoline(bounce.thunk())
}

fun root2(n: Double): Bounce<Double> = when {
    n < 1 -> Done(n)
    else -> More { divider2(sqrt(n)) }
}

fun divider2(n: Double): Bounce<Double> = when {
    n < 1 -> Done(n)
    else -> More { root2(n / 2) }
}

// 3 - 19
fun factorialWithTrampoline(n: BigDecimal, acc: BigDecimal = BigDecimal(1)): Bounce<BigDecimal> = when (n) {
    BigDecimal(0) -> Done(acc)
    else -> More { factorialWithTrampoline(n - BigDecimal(1), n * acc)}
}
