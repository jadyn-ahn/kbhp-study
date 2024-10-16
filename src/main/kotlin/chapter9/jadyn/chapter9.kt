package chapter9.jadyn

import chapter8.jadyn.FunList

interface Monoid<T> {
    fun mempty(): T

    fun mappend(m1: T, m2: T): T
}

fun <T> Monoid<T>.mconcat(list: FunList<T>): T = list.foldRight(mempty(), ::mappend)

class SumMonoid : Monoid<Int> {
    override fun mempty(): Int = 0

    override fun mappend(m1: Int, m2: Int): Int = m1 + m2
}

class AnyMonoid: Monoid<Boolean> {
    override fun mempty(): Boolean = false
    override fun mappend(m1: Boolean, m2: Boolean): Boolean = m1 || m2
}

class AllMonoid: Monoid<Boolean> {
    override fun mempty(): Boolean = true
    override fun mappend(m1: Boolean, m2: Boolean): Boolean = m1 && m2
}

// Maybe가 monoid다.. 라는 뜻이 무엇일까?
object MaybeMonoid {
    fun <T> monoid(inValue: Monoid<T>) = object Monoid<Maybe<T>> {
    }
}

class FunListMonoid<T>: Monoid<FunList<T>> {
    override fun mempty(): FunList<T> {
        TODO("Not yet implemented")
    }

    override fun mappend(m1: FunList<T>, m2: FunList<T>): FunList<T> {
        TODO("Not yet implemented")
    }
}