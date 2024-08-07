package chapter3.hyewon

fun power(x: Double, n: Int): Double {
    return when {
        n == 0 -> 1.0
        else -> x * power(x, n - 1)
    }
}

fun factorial(n: Int): Int = when {
    n == 1 -> 1
    else -> n * factorial(n - 1)
}

fun toBinary(n: Int): String = when {
    n == 1 -> "1"
    else -> "" + toBinary(n / 2) + n % 2
}

fun replicate(n: Int, element: Int): List<Int> = when {
    n == 0 -> listOf()
    else -> listOf(element) + replicate(n - 1, element)
}

fun elem(num: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    else -> list.first() == num || elem(num, list.takeLast(list.size - 1))
}
