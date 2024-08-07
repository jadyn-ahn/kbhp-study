package chapter3

import kotlin.math.max
import kotlin.math.min

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
