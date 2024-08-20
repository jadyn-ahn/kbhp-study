package chapter4.jiwon

import chapter4.ming.compose
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Chapter4Test : FreeSpec({
    "invokeOrElse" {
        val partialFunction1 = PartialFunction<Int, String>(
            { it.rem(2) == 0 },
            { "$it is even" }
        )
        val partialFunction2 = PartialFunction<Int, String>(
            { it.rem(2) == 1 },
            { "$it is odd" }
        )

        val result = partialFunction1.orElse(partialFunction2)(5)

        result shouldBe "5 is odd"
    }

    "partialAppliedFunction" {
        val func = { a: String,
                     b: String,
                     c: String ->
            a + b + c
        }

        val partiallyAppliedFunc1 = func.partial1("1")
        val result1 = partiallyAppliedFunc1("2", "3")
        result1 shouldBe "123"

        val partiallyAppliedFunc2 = func.partial2("1")
        val result2 = partiallyAppliedFunc2("2", "3")
        result2 shouldBe "213"

        val partiallyAppliedFunc3 = func.partial3("1")
        val result3 = partiallyAppliedFunc3("2", "3")
        result3 shouldBe "231"
    }

    "maxWith" {
        maxWith(4)(1) shouldBe 4
        maxWith(5)(10) shouldBe 10
    }

    "minCurried" {
        minCurried(1)(2) shouldBe 1
        minCurried(5)(2) shouldBe 2
    }

    "powerOfMax" {
        power(max(listOf(1, 2, 3))) shouldBe 9
        power(max(listOf(5, 3, 2))) shouldBe 25
    }

    "powerOfMax with compose" {
        val composed = power compose max
        composed(listOf(1, 2, 3)) shouldBe 9
    }

    "takeWhile" {
         takeWhile({ it < 3 }, listOf(1, 2, 3, 4, 5)) shouldBe listOf(1, 2)
    }

    "takeWhile with sequence" {
        val input = generateSequence(1) { it + 1 }
        takeWhile2({ it < 20 }, input) shouldBe listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
    }
})
