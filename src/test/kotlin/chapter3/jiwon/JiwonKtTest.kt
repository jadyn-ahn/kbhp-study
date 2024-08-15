package chapter3.jiwon

import chapter3.ming.factorial
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class JiwonKtTest : FreeSpec({
    "takeSequence" - {
        "n이 0일 때" {
            val result = takeSequence(0, sequenceOf(1, 2, 3))
            result shouldBe emptyList()
        }

        "n이 1일 때" {
            val result = takeSequence(1, sequenceOf(1, 2, 3))
            result shouldBe listOf(1)
        }

        "n이 sequence 개수보다 클 때" {
            val result = takeSequence(4, sequenceOf(1, 2, 3))
            result shouldBe listOf(1, 2, 3)
        }
    }

    "quickSort" - {
        "빈 list를 정렬한다" {
            val result = quickSort(listOf())
            result shouldBe emptyList()
        }

        "원소 하나 list를 정렬한다" {
            val result = quickSort(listOf(1))
            result shouldBe listOf(1)
        }

        "무작위 순서의 원소를 정렬한다" {
            val result = quickSort(listOf(4, 5, 2, 1, 3))
            result shouldBe listOf(1, 2, 3, 4, 5)
        }
    }

    "gcd" - {
        "2와 4의 최대공약수" {
            gcd(2, 4) shouldBe 2
        }

        "1과 1의 최대 공약수" {
            gcd(1, 1) shouldBe 1
        }

        "1과 0의 최대공약수" {
            gcd(1, 0) shouldBe 1
        }

        "12와 52의 최대공약수" {
            gcd(52, 12) shouldBe 4
        }
    }

    "factorialMemoization" {
        factorialMemoization(0) shouldBe 1
        factorialMemoization(1) shouldBe 1
        factorialMemoization(5) shouldBe 120
    }

    "factorialFP" {
        factorialFP(0) shouldBe 1
        factorialFP(1) shouldBe 1
        factorialFP(5) shouldBe 120
    }

    "powerTailRec" {
        power(0.0, 1) shouldBe 0.0
        power(0.0, 0) shouldBe 1.0
        power(2.0, 0) shouldBe 1.0
        power(2.0, 1) shouldBe 2.0
        power(2.0, 5) shouldBe 32.0
    }

    "toBinary" {
        toBinary(0) shouldBe "0"
        toBinary(1) shouldBe "1"
        toBinary(2) shouldBe "10"
        toBinary(10) shouldBe "1010"
    }

    "replicate" {
        replicate(0, 1) shouldBe emptyList()
        replicate(1, 1) shouldBe listOf(1)
        replicate(4, 1) shouldBe listOf(1, 1, 1, 1)
    }

    "elem" {
        elem(1, listOf(1, 2, 3)) shouldBe true
        elem(1, emptyList()) shouldBe false
        elem(1, listOf(3, 4, 1)) shouldBe true
        elem(1, listOf(3, 4, 5)) shouldBe false
    }

    "root/divider" {
        root(4.0) shouldBe 0.5
        root(1.0) shouldBe 0.5
    }

    "root/divider with trampoline" {
        trampoline(root2(4.0)) shouldBe 0.5
        trampoline(root2(1.0)) shouldBe 0.5
    }

    "factorial with trampoline" {
        trampoline(factorialWithTrampoline(BigDecimal(5))) shouldBe BigDecimal(120)
    }
})
