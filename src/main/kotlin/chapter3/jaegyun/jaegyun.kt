package chapter3.jaegyun

import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

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

// 3-7
fun takeSequence(n: Int, sequence: Sequence<Int>): List<Int> =
    when (n) {
        0 -> emptyList<Int>()
        else -> sequence.take(1).toList() + takeSequence(n -1, sequence.drop(1))
    }

// 3-8
// https://ko.wikipedia.org/wiki/%ED%80%B5_%EC%A0%95%EB%A0%AC
fun quicksort(list: List<Int>): List<Int> =
    when (list.size) {
        0 -> emptyList()
        1 -> list
        else -> {
            val pivot = list.head()
            val lessList = list.tail().filter { it <= pivot }
            val greaterList = list.tail().filter { it > pivot }
            quicksort(lessList) + pivot + quicksort(greaterList)
        }
    }

// 3-9
// https://ko.wikipedia.org/wiki/%EC%9C%A0%ED%81%B4%EB%A6%AC%EB%93%9C_%ED%98%B8%EC%A0%9C%EB%B2%95
fun gcd(m: Int, n: Int): Int =
    when {
        max(m, n) % min(m, n) == 0 -> min(m, n)
        else -> gcd(min(m, n), max(m, n) % min(m, n))
    }

// 3-10
// variable capture 활용
fun factorialMemoization(n: Int): Int {
    val memo = generateSequence { -1 }.take(n + 1).toMutableList()

    fun factorial(n: Int): Int =
        when {
            n in 0..1 -> 1
            memo[n] != -1 -> memo[n]
            else -> {
                println("factorial($n)")
                memo[n] = n * factorial(n - 1)
                memo[n]
            }
        }

    return factorial(n)
}

// 3-11, WIP
//fun factorialFunc(n: Int): Int {
//    fun factorialFuncInternal(n: Int, i: Int, fn1: Int): Int =
//        when {
//            n in 0..1 -> 1
//            else -> n * fn1
//        }
//
//    factorialFuncInternal(n, factorialFuncInternal(n -1, factorialFuncInternal(n - 2)))
//}

// 3-12
fun tailrecFactorial(n: Int): Int {
    tailrec fun internalTailrecFactorial(n: Int, result: Int = 1): Int =
        when (n) {
            0 -> result
            else -> internalTailrecFactorial(n - 1, result * n)
        }
    return internalTailrecFactorial(n)
}

// 3-13
fun tailrecPower(x: Double, n: Int): Double {
    tailrec fun internalPower(x: Double, n: Int, result: Double = 1.0): Double =
        when (n) {
            0 -> 1.0
            1 -> x
            else -> internalPower(x, n-1, result * x)
        }

    return internalPower(x, n)
}

// 3-14
fun tailedToBinary(n: Int): String {
    tailrec fun internalToBinary (n: Int, acc: String = ""): String = when {
        n <= 1 -> "$n$acc"
        else -> internalToBinary(n / 2, "${n % 2}$acc")
    }

    return internalToBinary(n)
}

// 3-15
fun tailedReplicate(n: Int, element: Int): List<Int> {
    tailrec fun internalReplicate(n: Int, element: Int, acc: List<Int> = emptyList()): List<Int> = when (n) {
        0 -> emptyList<Int>()
        else -> internalReplicate(n - 1, element, listOf(element) + acc)
    }
    return internalReplicate(n, element)
}

// 3-16
tailrec fun tailedElem(num: Int, list: List<Int>): Boolean =
    when (list.size) {
        0 -> false
        1 -> list.head() == num
        else -> tailedElem(num, list.tail())
    }

// 3-17
fun findFirstUnderOne(n: Double): Double = when {
    sqrt(n) / 2 < 1 -> n
    else -> findFirstUnderOne(sqrt(n) / 2)
}

fun <T> Set<T>.head() = first()
fun <T> Set<T>.tail() = drop(1).toSet()

tailrec fun <T> powerSet(s: Set<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
    s.isEmpty() -> acc
    else -> {
        powerSet(s.tail(), acc + acc.map { it + s.head() })
    }
}