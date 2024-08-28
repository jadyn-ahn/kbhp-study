package chapter5.hyewon

sealed class FunList<out T> {
    data object Nil : FunList<Nothing>()
    data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
}

val intList = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))
val doubleList = FunList.Cons(1.0, FunList.Cons(2.0, FunList.Cons(3.0, FunList.Nil)))

fun <T> FunList<T>.getHead(): T = when (this) {
    is FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> head
}

fun <T> FunList<T>.getTail(): FunList<T> = when (this) {
    is FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> tail
}

fun <T> FunList<T>.reverse(): FunList<T> {
    tailrec fun go(list: FunList<T>, acc: FunList<T>): FunList<T> = when (list) {
        is FunList.Nil -> acc
        is FunList.Cons -> go(list.tail, FunList.Cons(list.head, acc))
    }
    return go(this, FunList.Nil)
}

fun <T> FunList<T>.addHead(): FunList<T> = FunList.Cons(this.getHead(), this)

tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = when (this) {
    is FunList.Nil -> FunList.Nil
    is FunList.Cons -> if (n == 0) this else tail.drop(n - 1)
}

tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> = when (this) {
    is FunList.Nil -> FunList.Nil
    is FunList.Cons -> if (p(head)) tail.dropWhile(p) else this
}

tailrec fun <T> FunList<T>.take(n: Int, acc: FunList<T> = FunList.Nil): FunList<T> = when (n) {
    0 -> acc.reverse()
    else -> this.getTail().take(n - 1, FunList.Cons(this.getHead(), acc))
}

tailrec fun <T> FunList<T>.takeWhile(acc: FunList<T> = FunList.Nil, p: (T) -> Boolean): FunList<T> = when (this) {
    is FunList.Nil -> acc.reverse()
    is FunList.Cons -> if (p(head)) tail.takeWhile(FunList.Cons(head, acc), p) else acc.reverse()
}

tailrec fun <T, R> FunList<T>.map(acc: FunList<R> = FunList.Nil, f: (T) -> R): FunList<R> = when (this) {
    is FunList.Nil -> acc.reverse()
    is FunList.Cons -> tail.map(FunList.Cons(f(head), acc), f)
}

tailrec fun <T, R> FunList<T>.indexedMap(index: Int = 0, acc: FunList<R> = FunList.Nil, f: (Int, T) -> R): FunList<R> = when (this) {
    is FunList.Nil -> acc.reverse()
    is FunList.Cons -> tail.indexedMap(index + 1, FunList.Cons(f(index, head), acc), f)
}
