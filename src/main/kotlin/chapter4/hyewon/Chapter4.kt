package chapter4.hyewon

// 4-1
class PartialFunction<P, R>(
    private val f: (P) -> R,
    private val condition: (P) -> Boolean
) : (P) -> R {
    override fun invoke(p: P): R {
        if (condition(p)) {
            return f(p)
        } else {
            throw IllegalArgumentException("$p isn't supported.")
        }
    }

    fun isDefinedAt(p: P): Boolean = condition(p)

    fun invokeOrElse(p: P, default: R): R = when {
        isDefinedAt(p) -> invoke(p)
        else -> default
    }

    fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> =
        PartialFunction(
            { if (this.isDefinedAt(it)) this(it) else that(it) },
            { if (isDefinedAt(it)) this.isDefinedAt(it) else that.isDefinedAt(it) }
        )
}


// 4-2
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial1(p1: P1): (P2, P3) -> R {
    return { p2, p3 -> this(p1, p2, p3) }
}

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial2(p2: P2): (P1, P3) -> R {
    return { p1, p3 -> this(p1, p2, p3) }
}

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial3(p3: P3): (P1, P2) -> R {
    return { p1, p2 -> this(p1, p2, p3) }
}


// 4-3
fun max(x: Int) = { y: Int -> { if (x >= y) x else y } }


// 4-4
fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
    { p1: P1 -> { p2: P2 -> this(p1, p2) } }

fun min() = { x: Int, y: Int -> { if (x < y) x else y } }


// 4-5
val maxOf = { i: List<Int> -> i.max() }
val power = { x: Int -> x * x }
val squareOfMax = { i: List<Int> -> power(maxOf(i)) }


// 4-6
infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput)) }
}

val composedSquareOrMax = power compose maxOf
