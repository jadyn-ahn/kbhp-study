package chapter3.ming

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Ch3MingTest :
    StringSpec({
        "power test" {
            power(2.0, 0) shouldBe 1
            power(2.0, 5) shouldBe 32
            power(0.0, 5) shouldBe 0
            power(0.0, 0) shouldBe 1 // lim x->0 x^x
        }

        "factorial test" {
            factorial(0) shouldBe 1
            factorial(5) shouldBe 120
        }

        "toBinary test" {
            toBinary(0) shouldBe "0"
            toBinary(1) shouldBe "1"
            toBinary(10) shouldBe "1010"
        }

        "replicate test" {
            replicate(3, 5) shouldBe listOf(5, 5, 5)
            replicate(1, 5) shouldBe listOf(5)
            replicate(0, 5) shouldBe emptyList()
        }

        "elem test" {
            elem(3, listOf(2, 3, 4)) shouldBe true
            elem(1, listOf(2, 3, 4)) shouldBe false
            elem(1, listOf()) shouldBe false
        }

        "takeSequence test" {
            takeSequence(3, sequenceOf(1, 2, 3, 4, 5)) shouldBe listOf(1, 2, 3)
            takeSequence(0, sequenceOf(1, 2, 3, 4, 5)) shouldBe emptyList()
            takeSequence(3, sequenceOf()) shouldBe emptyList()
        }

        "quicksort test" {
            quicksort(listOf(2, 4, 1, 5, 3)) shouldBe listOf(1, 2, 3, 4, 5)
            quicksort(listOf()) shouldBe emptyList()
            quicksort(listOf(1)) shouldBe listOf(1)
        }

        "gcd test" {
            gcd(35, 25) shouldBe 5
            gcd(36, 24) shouldBe 12
            gcd(35, 0) shouldBe 0
        }

        "factorialMemo test" {
            factorialMemo(0) shouldBe 1
            factorialMemo(1) shouldBe 1
            factorialMemo(3) shouldBe 6
            factorialMemo(5) shouldBe 120
        }

        "factorialFP test" {
            factorialFP(0) shouldBe 1
            factorialFP(1) shouldBe 1
            factorialFP(3) shouldBe 6
            factorialFP(5) shouldBe 120
        }

        "powerTail test" {
            powerTail(2.0, 0) shouldBe 1
            powerTail(2.0, 5) shouldBe 32
            powerTail(0.0, 5) shouldBe 0
            powerTail(0.0, 0) shouldBe 1 // lim x->0 x^x
        }

        "toBinaryTail test" {
            toBinaryTail(0) shouldBe "0"
            toBinaryTail(1) shouldBe "1"
            toBinaryTail(10) shouldBe "1010"
        }

        "replicateTail test" {
            replicateTail(3, 5) shouldBe listOf(5, 5, 5)
            replicateTail(1, 5) shouldBe listOf(5)
            replicateTail(0, 5) shouldBe emptyList()
        }

        "elemTail test" {
            elemTail(3, listOf(2, 3, 4)) shouldBe true
            elemTail(1, listOf(2, 3, 4)) shouldBe false
            elemTail(1, listOf()) shouldBe false
        }
    })
