class Ch3Ming

// 3-2
fun power(x: Double, n: Int) : Double  = when {
    x == 0.0 -> if (n == 0) 1.0 else 0.0
    n == 0 -> 1.0
    else -> x * power(x, n - 1)
}

// 3-3
fun factorial(n: Int) : Int = when(n) {
    0 -> 1
    else -> n * factorial(n-1)
}

// 3-4
fun toBinary(n: Int) : String = when(n) {
    0 -> "0"
    1 -> "1"
    else  -> toBinary(n / 2) + n % 2
}

// 3-5
fun replicate(n: Int, element: Int): List<Int> = when {
    n < 0 -> emptyList()
    n ==  1 -> listOf(element)
    else -> listOf(element) + replicate(n-1, element)
}

// 3-6
//fun elem(num: Int, list: List<Int>): Boolean = when {
//
//}

