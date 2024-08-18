package chapter4.jiwon

import chapter4.ming.curried
import kotlin.math.max
import kotlin.math.min

// 4 - 1
class PartialFunction<P, R>(
    private val condition: (P) -> Boolean,
    private val f: (P) -> R
) : (P) -> R {
    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p isn't supported")
    }

    fun isDefinedAt(p: P): Boolean = condition(p)

    fun invokeOrElse(p: P, default: R): R = when {
        isDefinedAt(p) -> f(p)
        else -> default
    }

    fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> = PartialFunction(
        { this.isDefinedAt(it) || that.isDefinedAt(it) },
        {
            when {
                this.isDefinedAt(it) -> this(it)
                that.isDefinedAt(it) -> that(it)
                else -> throw IllegalArgumentException("$it isn't supported")
            }
        }
    )
}

// 4 - 2
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial1(p1: P1): (P2, P3) -> R {
    return { p2, p3 -> this(p1, p2, p3) }
}

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial2(p2: P2): (P1, P3) -> R {
    return { p1, p3 -> this(p1, p2, p3) }
}

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial3(p3: P3): (P1, P2) -> R {
    return { p1, p2 -> this(p1, p2, p3) }
}

// 4 - 3
fun maxWith(a: Int): (Int) -> Int {
    return { max(a, it) }
}

fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R {
    return { p1: P1 -> { p2: P2 -> this(p1, p2) } }
}

fun <P1, P2, R> ((P1) -> (P2) -> R).uncurried(): (P1, P2) -> R {
    return { p1: P1, p2: P2 -> this(p1)(p2) }
}

// 4 - 4
val loadMin = { a: Int, b: Int -> min(a, b) }
val minCurried = loadMin.curried()

infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { this(g(it)) }
}

// 4 - 5, 4 - 6
val max = { it: List<Int> -> it.max() }
val power = { it: Int -> it * it }
