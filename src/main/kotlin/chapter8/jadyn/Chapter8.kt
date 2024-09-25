package chapter8.jadyn

interface Functor<out A> {
    fun <B> fmap(f: (A) -> B): Functor<B>
}

interface Applicative<out A> : Functor<A> {

    fun <V> pure(value: V): Applicative<V>

    infix fun <B> apply(ff: Applicative<(A) -> B>): Applicative<B>
}

sealed class AMaybe<out A>: Applicative<A> {

    companion object {
        fun <V> pure(value: V): Applicative<V> = AJust(0).pure(value)
    }

    override fun <V> pure(value: V): Applicative<V> {
        TODO("Not yet implemented")
    }

    abstract override fun <B> apply(ff: Applicative<(A) -> B>): AMaybe<B>
}

data class AJust<out A>(val value: A): AMaybe<A>() {
    override fun <B> apply(ff: Applicative<(A) -> B>): AMaybe<B> = when (ff) {
        is AJust -> fmap(ff.value)
        else -> ANothing
    }

    override fun <B> fmap(f: (A) -> B): AMaybe<B> = AJust(f(value))
}

data object ANothing : AMaybe<Nothing>() {
    override fun <B> fmap(f: (Nothing) -> B): Functor<B> = ANothing

    override fun <B> apply(ff: Applicative<(Nothing) -> B>): AMaybe<B> = ANothing
}

fun a() {
    val a = AMaybe.pure({ x: Int -> x * 2 })
    a.apply(AMaybe.pure({ f: (x: Int) -> Int -> 100 })) // 책에서의 설명이 이상한듯.. apply 함수의 파라미터 타입이 Applicative<(A) -> B> 라는 함수 형태로 고정되어 있어서 그런 것으로 보임.
}

// 8-3
sealed class FunList<out A>: Functor<A> {
    abstract override fun <B> fmap(f: (A) -> B): FunList<B>
    companion object
}

data class Cons<out A>(val head: A, val tail: FunList<A>): FunList<A>() {
    override fun <B> fmap(f: (A) -> B): Cons<B> = Cons(f(head), tail.fmap(f))
}

data object Nil : FunList<Nothing>() {
    override fun <B> fmap(f: (Nothing) -> B): FunList<B> = Nil
}

fun <T> FunList.Companion.pure(value: T): FunList<T> = Cons(value, Nil)

infix fun <A> FunList<A>.append(other: FunList<A>): FunList<A> = TODO()

infix fun <A, B> FunList<(A) -> B>.apply(f: FunList<A>): FunList<B> = when (this) {
    is Nil -> Nil
    is Cons -> f.fmap(head).append(tail.apply(f))
}
