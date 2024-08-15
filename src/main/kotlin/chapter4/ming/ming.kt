package chapter4.ming

import chapter3.ming.head
import chapter3.ming.powerTail
import chapter3.ming.tail

// 4-1
class PartialFunction<P, R>(
    private val condition: (P) -> Boolean,
    private val f: (P) -> R,
) : (P) -> R {
    override fun invoke(p: P): R =
        when {
            condition(p) -> f(p)
            else -> throw IllegalArgumentException("$p isn't supported")
        }

    fun isDefinedAt(p: P): Boolean = condition(p)

    fun invokeOrElse(
        p: P,
        default: R,
    ): R =
        when {
            condition(p) -> f(p)
            else -> default
        }

    fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> =
        PartialFunction(
            { this.isDefinedAt(it) || that.isDefinedAt(it) },
            {
                when {
                    this.isDefinedAt(it) -> this(it)
                    that.isDefinedAt(it) -> that(it)
                    else -> throw IllegalArgumentException("")
                }
            },
        )
}

// 4-2
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial1(p1: P1): (P2, P3) -> R = { p2, p3 -> this(p1, p2, p3) }

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial2(p2: P2): (P1, P3) -> R = { p1, p3 -> this(p1, p2, p3) }

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial3(p3: P3): (P1, P2) -> R = { p1, p2 -> this(p1, p2, p3) }

// 4-3
fun max(a: Int) = { b: Int -> if (a > b) a else b }

// 4-4
fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1: P1 -> { p2: P2 -> this(p1, p2) } }

fun <P1, P2, R> ((P1) -> (P2) -> R).unCurried(): (P1, P2) -> R = { p1: P1, p2: P2 -> this(p1)(p2) }

// 4-5, 4-6
val maxValue = { l: List<Int> -> l.max() }
val powerOfTwo = { x: Int -> powerTail(x.toDouble(), 2) }
val composedFunc1 = { l: List<Int> -> powerOfTwo(maxValue(l)) }
val composedFunc2 = powerOfTwo compose maxValue

infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R = { gValue: G -> this(g(gValue)) }

// 4-7
tailrec fun <P> takeWhile(
    f: (P) -> Boolean,
    list: List<P>,
    acc: List<P> = listOf(),
): List<P> =
    when {
        list.isEmpty() -> acc
        else -> {
            val element = list.head()
            val list2 = list.tail()
            if (f(element)) takeWhile(f, list2, acc + element) else takeWhile(f, list2, acc)
        }
    }
