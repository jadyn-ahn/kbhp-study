package chapter4.jadyn

import chapter3.jaegyun.head
import chapter3.jaegyun.tail

class PartialFunction<P, R> (
    private val condition: (P) -> Boolean,
    private val f: (P) -> R
): (P) -> R {

    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p isn't supported")
    }

    //4-1
    fun invokeOrElse(p: P, default: R): R = when {
        this.condition(p) -> f(p)
        else -> default
    }

    fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R>
        = PartialFunction(condition) { p -> this.invokeOrElse(p, that(p)) }
}

// 4-2
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial1(p1: P1): (P2, P3) -> R = { p2, p3 -> this(p1, p2, p3) }
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial2(p2: P2): (P1, P3) -> R = { p1, p3 -> this(p1, p2, p3) }
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial3(p3: P3): (P1, P2) -> R = { p1, p2 -> this(p1, p2, p3) }


// 4-3
fun max(a: Int): (Int) -> Int = { b -> if (a > b) a else b }

// 4-4
fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
    { p1: P1 -> { p2: P2 -> this(p1, p2) } }

fun fourFour() {
    val min = { a: Int, b: Int -> if (a > b) b else a }
    val minCurried = min.curried()
}

// 4-5,6
fun fourFiveSix() {
    fun max(a: List<Int>): Int = a.max()

    fun power(a: Int): Int = a * a

    val maxPower = ::power compose ::max
}

infix fun <P1, P2, R> ((P1) -> R).compose(f: (P2) -> P1): (P2) -> R = { p2: P2 -> this(f(p2)) }

// 4-7
fun seven() {
    tailrec fun <T> takeWhile(l: List<T>, f: (T) -> Boolean, acc: List<T> = ArrayList()): List<T> = when {
        l.isEmpty() -> acc
        else -> if (!f(l.head())) acc else takeWhile(l.tail(), f, acc + l.head())
    }

    val sequence = generateSequence(1) { it -> it + 1 }

    tailrec fun <T> takeWhile(s: Sequence<T>, f: (T) -> Boolean, acc: Sequence<T> = emptySequence()): Sequence<T> = when {
        s.none() -> acc
        else -> if (!f(s.first())) acc else takeWhile(s.drop(1), f, acc + s.first())
    }
}
