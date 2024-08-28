package chapter5.hyewon

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Chapter5Test : FunSpec({

    test("5-3 getHead") {
        val list = FunList.Nil
        shouldThrow<NoSuchElementException> { list.getHead() }
    }

    test("5-4 drop") {
        val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))
        list.drop(0) shouldBe FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))
        list.drop(2) shouldBe FunList.Cons(3, FunList.Nil)
        list.drop(3) shouldBe FunList.Nil
    }

    test("5-5 dropWhile") {
        val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Nil))))
        list.dropWhile { it < 3 } shouldBe FunList.Cons(3, FunList.Cons(4, FunList.Nil))
    }

    test("5-6 take") {
        val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Nil))))
        list.take(2) shouldBe FunList.Cons(1, FunList.Cons(2, FunList.Nil))
    }

    test("5-7 takeWhile") {
        val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Nil))))
        list.takeWhile { it < 3 } shouldBe FunList.Cons(1, FunList.Cons(2, FunList.Nil))
    }

    test("map") {
        val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))
        list.map { it * 2 } shouldBe FunList.Cons(2, FunList.Cons(4, FunList.Cons(6, FunList.Nil)))
    }

    test("5-8 indexedMap") {
        val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))
        list.indexedMap { i, v -> i * v } shouldBe FunList.Cons(0, FunList.Cons(2, FunList.Cons(6, FunList.Nil)))
    }
})
