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

// 5-3
fun <T> FunList<T>.getHead(): T = when (this) {
    is Nil -> throw NoSuchElementException()
    is Cons -> head
}

// 5-4
tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = if (n == 0) this else when(this) {
    is Nil -> Nil
    is Cons -> tail.drop(n-1)
}

// 5-5
tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> = when(this) {
    is Nil -> Nil
    is Cons -> if (p(head)) this else tail.dropWhile(p)
}

// 5-6
tailrec fun <T> FunList<T>.take(n: Int, acc: FunList<T> = Nil): FunList<T> = if (n == 0) this else when(this) {
    is Nil -> acc.reverse()
    is Cons -> tail.take(n - 1, Cons(head, acc))
}

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = Nil): FunList<T> = when (this) {
    is Nil -> acc
    is Cons -> tail.reverse(Cons(head, acc))
}

// 5-7
tailrec fun <T> FunList<T>.takeWhile(p: (T) -> Boolean, acc: FunList<T> = Nil): FunList<T> = when (this) {
    is Nil -> acc.reverse()
    is Cons -> if (p(head)) acc.reverse() else tail.takeWhile(p, Cons(head, acc))
}

// 5-8
tailrec fun <T, R> FunList<T>.indexedMap(index: Int = 0, acc: FunList<R> = Nil, f: (Int, T) -> R): FunList<R> = when (this) {
    is Nil -> acc.reverse()
    is Cons -> tail.indexedMap(index + 1, Cons(f(index, head), acc), f)
}

tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    is Nil -> acc
    is Cons -> tail.foldLeft(f(acc, head), f)
}

// 5-9
fun FunList<Int>.maximumByFoldLeft(): Int = foldLeft(Int.MIN_VALUE) { a, b -> if (a > b) a else b }

// 5-10
fun <T> FunList<T>.filterByFoldLeft(p: (T) -> Boolean): FunList<T> = foldLeft(Nil) { acc: FunList<T>, head: T -> if (p(head)) Cons(head, acc) else acc }

fun <T, R> FunList<T>.foldRight(acc: R, f: (T, R) -> R): R = when (this) {
    is Nil -> acc
    is Cons -> f(head, tail.foldRight(acc, f))
}

// 5-11
fun <T> FunList<T>.reverse() = foldRight(Nil) { head: T, acc: FunList<T> -> Cons(head, acc)}

// 5-12
fun <T> FunList<T>.filterByFoldRight(p: (T) -> Boolean) = foldRight(Nil) { head: T, acc: FunList<T> -> if (p(head)) Cons(head, acc) else acc }

// 5-13
tailrec fun <T, R> FunList<T>.zip(other: FunList<R>, acc: FunList<Pair<T, R>> = Nil): FunList<Pair<T, R>> = when {
    this is Nil || other is Nil -> acc.reverse()
    else -> {
        val thisCons = this as Cons
        val otherCons = other as Cons
        thisCons.tail.zip(otherCons.tail, Cons(thisCons.head to otherCons.head, acc))
    }
}

// 5-14
fun <T, R> FunList<T>.associate(f: (T) -> Pair<T, R>): Map<T, R> = mapOf() // mapOf(this.map(::f))

// 5-15
fun <T, K> FunList<T>.groupBy(f: (T) -> K): Map<K, FunList<T>> = TODO() // zip(this.map(f)).foldLeft(...)

sealed class FunStream<out T> {
    data object Nil : FunStream<Nothing>()
    data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>): FunStream<T>()
}

// 5-17
fun <T> FunStream<Int>.sum(): Int = when (this) {
    is FunStream.Nil -> throw RuntimeException()
    is FunStream.Cons -> head() + tail().sum<Int>()
}

// 5-19
fun <T> FunStream<T>.appendTail(value: T): FunStream<T> = when (this) {
    is FunStream.Nil -> FunStream.Cons({ value }, { FunStream.Nil })
    is FunStream.Cons -> FunStream.Cons(head, { tail().appendTail(value) })
}

// 5-20
fun <T> FunStream<T>.filter(p: (T) -> Boolean): FunStream<T> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> if (p(head())) FunStream.Cons(head, { tail().filter(p) }) else tail().filter(p)
}

// 5-21
fun <T, R> FunStream<T>.map(f: (T) -> R): FunStream<R> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> FunStream.Cons({ f(head())}, { tail().map(f) })
}
