package chapter7.hyewon

interface Functor<out A> {
    fun <B> fmap(f: (A) -> B): Functor<B>
}

sealed class FunList<out A> : Functor<A> {
    abstract override fun <B> fmap(f: (A) -> B): FunList<B>
    abstract fun first(): A
    abstract fun size(): Int
}

data object Nil : FunList<Nothing>() {
    override fun <B> fmap(f: (Nothing) -> B): FunList<B> = this
    override fun first(): Nothing = throw NoSuchElementException()
    override fun size(): Int = 0
}

data class Cons<out A>(val head: A, val tail: FunList<A>) : FunList<A>() {
    override fun <B> fmap(f: (A) -> B): FunList<B> = Cons(f(head), tail.fmap(f))
    override fun first(): A = head
    override fun size(): Int = 1 + tail.size()
}

fun <T> identity(x: T): T = x

infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput)) }
}
