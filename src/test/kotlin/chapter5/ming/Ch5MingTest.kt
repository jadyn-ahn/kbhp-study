package chapter5.ming

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Ch5MingTest :
    DescribeSpec({
        describe("FunList") {
            val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))

            it("addHead") {
                list.addHead(0) shouldBe FunList.Cons(0, FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil))))
            }
            it("appendTail") {
                list.appendTail(4) shouldBe FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Nil))))
            }
            it("addTail") {
                list.addTail(4) shouldBe FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Nil))))
            }
            it("reverse") {
                list.reverse() shouldBe FunList.Cons(3, FunList.Cons(2, FunList.Cons(1, FunList.Nil)))
            }
            it("getTail") {
                list.getTail() shouldBe FunList.Cons(2, FunList.Cons(3, FunList.Nil))
            }
            it("getHead") {
                list.getHead() shouldBe 1
            }
            it("drop") {
                list.drop(2) shouldBe FunList.Cons(3, FunList.Nil)
            }
            it("dropWhile") {
                list.dropWhile({ it < 3 }) shouldBe FunList.Cons(3, FunList.Nil)
            }
            it("take") {
                list.take(1) shouldBe FunList.Cons(1, FunList.Nil)
            }
            it("takeWhile") {
                list.takeWhile({ it < 2 }) shouldBe FunList.Cons(1, FunList.Nil)
            }
            it("indexedMap") {
                val result = list.indexedMap { i, element -> if (i % 2 == 0) element * 2 else element }

                result shouldBe FunList.Cons(2, FunList.Cons(2, FunList.Cons(6, FunList.Nil)))
            }
        }
    })
