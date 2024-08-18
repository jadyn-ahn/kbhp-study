package chapter4.jiwon

class PartialFunction<P, R> (
    private val condition: (P) -> Boolean,
    private val f: (P) -> R
): (P) -> R {
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
