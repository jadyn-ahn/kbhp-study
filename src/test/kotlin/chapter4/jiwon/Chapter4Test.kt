package chapter4.jiwon

import chapter4.jiwon.partial1
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
        val func = {
            a: String,
            b: String,
            c: String -> a+b+c
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
})
