package chapter4.ming

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
