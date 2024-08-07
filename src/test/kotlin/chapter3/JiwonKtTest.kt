package chapter3

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

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
})
