package chapter4.ming

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Ch4MingTest :
    DescribeSpec({
        describe("PartialFunction") {
            it("isDefinedAt") {
                val isEven = PartialFunction<Int, String>({ it.rem(2) == 0 }, { "$it is even" })

                isEven.isDefinedAt(2) shouldBe true
                isEven.isDefinedAt(1) shouldBe false
            }
            it("invoke") {
                val isEven = PartialFunction<Int, String>({ it.rem(2) == 0 }, { "$it is even" })

                isEven(2) shouldBe "2 is even"
                shouldThrow<IllegalArgumentException> { isEven(1) }.message shouldBe "1 isn't supported"
            }
            it("invokeOrElse") {
                val isEven = PartialFunction<Int, String>({ it.rem(2) == 0 }, { "$it is even" })

                isEven.invokeOrElse(2, "Not supported") shouldBe "2 is even"
                isEven.invokeOrElse(1, "Not supported") shouldBe "Not supported"
            }
            it("orElse") {
                val isEven = PartialFunction<Int, String>({ it.rem(2) == 0 }, { "$it is even" })
                val isOdd = PartialFunction<Int, String>({ it.rem(2) == 1 }, { "$it is odd" })
                val f = isEven.orElse(isOdd)

                f(4) shouldBe "4 is even"
                f(1) shouldBe "1 is odd"
            }
        }
        describe("PartiallyAppliedFunction") {
            it("partialN") {
                val func = { a: Int, b: Int, c: Int -> a + b + c }

                val addOneCalculator = func.partial1(1)
                val addTwoCalculator = func.partial2(2)
                val addThreeCalculator = func.partial3(3)

                addOneCalculator(2, 3) shouldBe 6
                addTwoCalculator(2, 3) shouldBe 7
                addThreeCalculator(2, 3) shouldBe 8
            }
        }
    })
