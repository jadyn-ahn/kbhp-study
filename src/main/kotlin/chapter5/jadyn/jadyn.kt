package chapter5.jadyn

sealed class FunList<out T>
data object Nil: FunList<Nothing>()
data class Cons<out T>(val head: T, val tail: FunList<T>): FunList<T>()

// 5-1
fun one() {
    val a: FunList<Int> = Cons( 1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
    val b: FunList<Double> = Cons( 1.0, Cons(2.0, Cons(3.0, Cons(4.0, Cons(5.0, Nil)))))
}

// 5-2
fun two() {
    fun <T> FunList<T>.getHead(): T = when (this) {
        is Nil -> throw NoSuchElementException()
        is Cons -> head
    }
}