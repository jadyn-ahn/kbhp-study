package chapter4.hyewon

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe


class Chapter4Test : FunSpec({

    test("4-1 invokeOrElse") {
        val condition: (Int) -> Boolean = { it > 0 }
        val body: (Int) -> String = { "$it is a natural number" }
        val partialFunction = PartialFunction(body, condition)

        val default = partialFunction.invokeOrElse(-1, "it is not a natural number")
        default shouldBe "it is not a natural number"

        val invoked: String = partialFunction.invokeOrElse(1, "bye")
        invoked shouldBe "1 is a natural number"
    }

    test("4-1 orElse") {
        val naturalNumberCondition: (Int) -> Boolean = { it > 0 }
        val naturalNumberBody: (Int) -> String = { "$it is a natural number" }
        val naturalNumber = PartialFunction(naturalNumberBody, naturalNumberCondition)

        val condition: (Int) -> Boolean = { it <= 0 }
        val body: (Int) -> String = { "$it is not a natural number" }
        val notNaturalNumber = PartialFunction(body, condition)

        val result = listOf(0, 1).map { naturalNumber.orElse(notNaturalNumber)(it) }
        result[0] shouldBe "0 is not a natural number"
        result[1] shouldBe "1 is a natural number"
    }

    test("4-2 부분 적용 함수") {
        val func = { a: Int, b: Int, c: Int -> a * b * c }
        val resultWithoutPartial = func(1, 2, 3)
        resultWithoutPartial shouldBe 6

        val partialAsParam1: (Int, Int) -> Int = func.partial1(0)
        val resultWithParam1 = partialAsParam1(2, 3)
        resultWithParam1 shouldBe 0

        val partialAsParam2: (Int, Int) -> Int = func.partial2(0)
        val resultWithParam2 = partialAsParam2(1, 3)
        resultWithParam2 shouldBe 0

        val partialAsParam3: (Int, Int) -> Int = func.partial3(0)
        val resultWithParam3 = partialAsParam3(1, 2)
        resultWithParam3 shouldBe 0
    }

    test("4-3 더 큰 수 찾기") {
        max(10)(20)() shouldBe 20
        max(30)(20)() shouldBe 30
    }

    test("4-4 더 작은 수 찾기") {
        val curried = min().curried()
        curried(10)(20)() shouldBe 10
        curried(30)(20)() shouldBe 20
    }

    test("4-5 최대값의 제곱") {
        val result = squareOfMax(listOf(1, 5, 2))
        result shouldBe 25
    }

    test("4-6 composed 사용한 최대값의 제곱") {
        val result = composedSquareOrMax(listOf(1, 5, 2))
        result shouldBe 25
    }
})
