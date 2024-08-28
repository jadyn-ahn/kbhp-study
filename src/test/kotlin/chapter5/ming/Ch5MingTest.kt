package chapter5.ming

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Ch5MingTest :
    DescribeSpec({
        describe("FunList") {
            it("addHead") {
                val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))

                list.addHead(0) shouldBe FunList.Cons(0, FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil))))
            }
            it("appendTail") {
                val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))

                list.appendTail(4) shouldBe FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Nil))))
            }
            it("addTail") {
                val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))

                list.addTail(4) shouldBe FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Nil))))
            }
            it("reverse") {
                val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))

                list.reverse() shouldBe FunList.Cons(3, FunList.Cons(2, FunList.Cons(1, FunList.Nil)))
            }
            it("getTail") {
                val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))

                list.getTail() shouldBe FunList.Cons(2, FunList.Cons(3, FunList.Nil))
            }
            it("getHead") {
                val list = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Nil)))

                list.getHead() shouldBe 1
            }
        }
    })
