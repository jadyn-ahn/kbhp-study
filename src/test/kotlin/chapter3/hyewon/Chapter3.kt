package chapter3.hyewon

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Chapter3 : FunSpec({

    test("x의 n승") {
        power(2.0, 0) shouldBe 1
        power(2.0, 5) shouldBe 32
    }

    test("n의 팩토리얼") {
        factorial(1) shouldBe 1
        factorial(5) shouldBe 120
        factorial(10) shouldBe 3628800
    }

    test("10진수 숫자를 2진수 문자열로") {
        toBinary(1) shouldBe "1"
        toBinary(3) shouldBe "11"
        toBinary(4) shouldBe "100"
        toBinary(8) shouldBe "1000"
        toBinary(11) shouldBe "1011"
    }

    test("n개의 element를 가진 list") {
        replicate(3, 5) shouldBe listOf(5, 5, 5)
        replicate(1, 5) shouldBe listOf(5)
        replicate(0, 5) shouldBe listOf()
    }

    test("num이 list에 있는가") {
        elem(1, listOf(0, 2, 3)) shouldBe false
        elem(1, listOf(0, 1, 2, 3)) shouldBe true
        elem(1, listOf()) shouldBe false
    }
})
