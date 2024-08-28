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

// 5-4
tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = when {
    n == 0 -> this
    else -> {
        this.getTail().drop(n - 1)
    }
}

// 5 - 5
tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> = when {
    this == FunList.Nil -> this
    p(this.getHead()) -> this.getTail()
    else -> {
        this.getTail().dropWhile(p)
    }
}

// 5 - 8
tailrec fun <T, R> FunList<T>.indexedMap(index: Int = 0, acc: FunList<R> = FunList.Nil, f: (Int, T) -> R): FunList<R> = when(this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> tail.indexedMap(index + 1, acc.addHead(f(index, this.getHead())), f)
}

tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when(this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.foldLeft(f(acc, head), f)
}

// 5 - 9
fun FunList<Int>.maximumByFoldLeft(): Int = this.foldLeft(0) {
    acc, x -> if (acc > x) acc else x
}

sealed class FunStream<out T> {
    object Nil: FunStream<Nothing>()
    data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>): FunStream<T>()
}

tailrec fun <T, R> FunStream<T>.foldLeft(acc: R, f: (R, T) -> R): R = when(this) {
    FunStream.Nil -> acc
    is FunStream.Cons -> tail().foldLeft(f(acc, head()), f)
}

fun FunStream<Int>.sum(): Int {
    return this.foldLeft(0) { acc, v -> acc + v }
}
