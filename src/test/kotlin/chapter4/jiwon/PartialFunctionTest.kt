package chapter4.jiwon

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PartialFunctionTest : FreeSpec({
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
})
