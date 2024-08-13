package chapter3.jiwon

import kotlin.math.max
import kotlin.math.min

fun<T> List<T>.head(): T = when (this.size) {
    0 -> throw RuntimeException("no element in list")
    else -> this[0]
}

fun<T> List<T>.tail(): List<T> = when (this.size) {
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
            memo[num] = num * factorial(num -1)
            memo[num]
        }
    }

    return factorial(n)
}

// 3 - 11 / 12
fun factorialFP(n: Int): Int = factorialFP(n, 1)

tailrec fun factorialFP(n: Int, medium: Int): Int = when(n) {
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
