package chapter5.ming

import chapter5.ming.FunList.Cons

sealed class FunList<out T> {
    object Nil : FunList<Nothing>()

    data class Cons<out T>(
        val head: T,
        val tail: FunList<T>,
    ) : FunList<T>()
}

fun <T> FunList<T>.addHead(value: T): FunList<T> = Cons(value, this)

fun <T> FunList<T>.appendTail(value: T): FunList<T> =
    when (this) {
        FunList.Nil -> Cons(value, FunList.Nil)
        is FunList.Cons -> Cons(head, tail.appendTail(value))
    }

tailrec fun <T> FunList<T>.addTail(
    value: T,
    acc: FunList<T> = FunList.Nil,
): FunList<T> =
    when (this) {
        FunList.Nil -> Cons(value, acc).reverse()
        is FunList.Cons -> tail.addTail(value, acc.addHead(head))
    }

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> =
    when (this) {
        FunList.Nil -> acc
        is FunList.Cons -> tail.reverse(acc.addHead(head))
    }

fun <T> FunList<T>.getTail(): FunList<T> =
    when (this) {
        FunList.Nil -> throw NoSuchElementException()
        is FunList.Cons -> tail
    }

// 5-3
fun <T> FunList<T>.getHead(): T =
    when (this) {
        FunList.Nil -> throw NoSuchElementException()
        is FunList.Cons -> head
    }

// 5-1
val intList = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, FunList.Nil)))))

// 5-2
val doubleList = Cons(1.0, Cons(2.0, Cons(3.0, Cons(4.0, Cons(5.0, FunList.Nil)))))

// 5-4
tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> =
    when {
        this is FunList.Cons && n > 0 -> tail.drop(n - 1)
        else -> this
    }

// 5-5
tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> =
    when {
        this is FunList.Cons && p(head) -> tail.dropWhile(p)
        else -> this
    }

// 5-6
tailrec fun <T> FunList<T>.take(
    n: Int,
    acc: FunList<T> = FunList.Nil,
): FunList<T> =
    when (this) {
        FunList.Nil -> acc
        is FunList.Cons ->
            if (n > 0) {
                tail.take(n - 1, acc.addHead(head))
            } else {
                tail.take(n, acc)
            }
    }

// 5-7
tailrec fun <T> FunList<T>.takeWhile(
    p: (T) -> Boolean,
    acc: FunList<T> = FunList.Nil,
): FunList<T> =
    when (this) {
        FunList.Nil -> acc
        is FunList.Cons ->
            if (p(head)) {
                tail.takeWhile(p, acc.addHead(head))
            } else {
                tail.takeWhile(p, acc)
            }
    }

// 5-8
tailrec fun <T, R> FunList<T>.indexedMap(
    index: Int = 0,
    acc: FunList<R> = FunList.Nil,
    f: (Int, T) -> R,
): FunList<R> =
    when (this) {
        FunList.Nil -> acc.reverse()
        is FunList.Cons -> tail.indexedMap(index + 1, acc.addHead(f(index, head)), f)
    }
