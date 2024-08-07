package chapter3.ming

import kotlin.math.max
import kotlin.math.min

fun <T> List<T>.head(): T =
    when {
        this.isEmpty() -> throw IllegalArgumentException()
        else -> this.get(0)
    }

fun <T> List<T>.tail(): List<T> =
    when {
        this.isEmpty() -> throw IllegalArgumentException()
        else -> this.drop(1)
    }

// 3-2
fun power(
    x: Double,
    n: Int,
): Double =
    when {
        x == 0.0 -> if (n == 0) 1.0 else 0.0
        n == 0 -> 1.0
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
fun replicate(
    n: Int,
    element: Int,
): List<Int> =
    when {
        n <= 0 -> emptyList()
        n == 1 -> listOf(element)
        else -> listOf(element) + replicate(n - 1, element)
    }

// 3-6
fun elem(
    num: Int,
    list: List<Int>,
): Boolean =
    when {
        list.isEmpty() -> false
        else -> if (list.head() == num) true else elem(num, list.tail())
    }

// 3-7
fun takeSequence(
    n: Int,
    sequence: Sequence<Int>,
): List<Int> =
    when {
        n <= 0 -> emptyList()
        sequence.none() -> emptyList()
        else -> listOf(sequence.first()) + takeSequence(n - 1, sequence.drop(1))
    }

// 3-8
fun quicksort(list: List<Int>): List<Int> =
    when {
        list.isEmpty() -> emptyList()
        list.size == 1 -> list
        else -> {
            val pivot = list.head()
            val partition = list.tail().partition { it < pivot }
            println(pivot)
            println(partition)
            quicksort(partition.first) + listOf(pivot) + quicksort(partition.second)
        }
    }

// 3-9
fun gcd(
    m: Int,
    n: Int,
): Int =
    when {
        m == 0 || n == 0 -> 0
        max(m, n) % min(m, n) == 0 -> min(m, n)
        else -> gcd(min(m, n), max(m, n) % min(m, n))
    }

// 3-10
fun factorialMemo(n: Int): Int {
    var memo = MutableList(n + 1) { -1 }

    fun factorial(n: Int): Int =
        when {
            n <= 1 -> 1
            memo[n] != -1 -> memo[n]
            else -> {
                memo[n] = n * factorial(n - 1)
                memo[n]
            }
        }

    return factorial(n)
}

// 3-11, 3-12
tailrec fun factorialFP(
    n: Int,
    acc: Int = 1,
): Int =
    when (n) {
        0 -> acc
        1 -> acc
        else -> factorialFP(n - 1, acc * n)
    }

// 3-13
tailrec fun powerTail(
    x: Double,
    n: Int,
    acc: Double = 1.0,
): Double =
    when {
        x == 0.0 -> if (n == 0) 1.0 else 0.0
        n == 0 -> acc
        else -> powerTail(x, n - 1, acc * x)
    }

// 3-14
tailrec fun toBinaryTail(
    n: Int,
    acc: String = "",
): String =
    when (n) {
        0 -> if (acc.isEmpty()) "0" else acc
        1 -> "1" + acc
        else -> toBinaryTail(n / 2, (n % 2).toString() + acc)
    }

// 3-15
tailrec fun replicateTail(
    n: Int,
    element: Int,
    acc: List<Int> = emptyList(),
): List<Int> =
    when {
        n <= 0 -> acc
        else -> replicateTail(n - 1, element, acc + listOf(element))
    }

// 3-16
tailrec fun elemTail(
    num: Int,
    list: List<Int>,
    acc: Boolean = false,
): Boolean =
    when {
        list.isEmpty() -> acc
        else -> {
            val head = list.head()
            val contains = acc || head == num
            elemTail(num, list.tail(), contains)
        }
    }
