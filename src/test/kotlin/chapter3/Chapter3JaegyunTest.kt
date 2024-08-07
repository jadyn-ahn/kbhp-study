package chapter3

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Chapter3JaegyunTest : StringSpec({

    "2의 4승은 16" {
        power(2.0, 4) shouldBe 16
    }

    "3의 0승은 1" {
        power(3.0, 0) shouldBe 1
    }

    "-4의 3승은 -64" {
        power(-4.0, 3) shouldBe -64
    }

    "0! = 1" {
        factorial(0) shouldBe 1
    }

    "1! = 1" {
        factorial(1) shouldBe 1
    }

    "10! = 3628800" {
        factorial(10) shouldBe 3628800
    }

    "1 -> 1" {
        toBinary(1) shouldBe "1"
    }
    
    "2 -> 10" {
        toBinary(2) shouldBe "10"
    }

    "9 -> 1001" {
        toBinary(9) shouldBe "1001"
    }

    "replicate 7 by 1 times" {
        replicate(1, 7) shouldBe listOf(7)
    }

    "replicate 3 by 4 times" {
        replicate(3, 4) shouldBe listOf(4, 4, 4)
    }

    "find 2 in {}" {
        elem(2, listOf()) shouldBe false
    }

    "find 2 in {1, 2, 3}" {
        elem(2, listOf(1, 2, 3)) shouldBe true
    }

    "find 3 in {1, 2, 2, 4}" {
        elem(3, listOf(1, 2, 2, 4)) shouldBe false
    }
})