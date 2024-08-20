package chapter5.jiwon

sealed class FunList<out T> {
    data object Nil : FunList<Nothing>()
    data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
}

// 5 - 1
val intList: FunList<Int> = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Nil))))

// 5 - 2
val doubleList: FunList<Double> = FunList.Cons(1.0, FunList.Cons(2.0, FunList.Cons(3.0, FunList.Cons(4.0, FunList.Nil))))


fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)

tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> FunList.Cons(value, acc).reverse()
    is FunList.Cons -> tail.appendTail(value, acc.addHead(head))
}

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.reverse(acc.addHead(head))
}

fun <T> FunList<T>.getTail(): FunList<T> = when(this) {
    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> tail
}

// 5 - 3
fun <T> FunList<T>.getHead(): T = when(this) {
    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> head
}
