package chapter7.hyewon

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Chapter7Test : FunSpec({

    test("7-1 Functor") {
        val list = Cons(1, Cons(2, Cons(3, Nil)))
        val result = list.fmap { it * 2 }
        result shouldBe Cons(2, Cons(4, Cons(6, Nil)))
        result.first() shouldBe 2
        result.size() shouldBe 3
    }

    test("7-2 법칙 1 검증") {
        val list = Cons(1, Cons(2, Cons(3, Nil)))
        list.fmap { identity(it) } shouldBe identity(list)
    }

    test("7-2 법칙 2 검증") {
        val f = { a: Int -> a + 1 }
        val g = { b: Int -> b * 2 }

        val list = Cons(1, Cons(2, Cons(3, Nil)))
        list.fmap { f compose g } shouldBe list.fmap(g).fmap(f)
    }
})
