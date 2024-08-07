package chapter3

// 3-2
fun power(x: Double, n: Int): Double =
    when (n) {
        0 -> 1.0
        else -> x * power(x, n - 1)
    }