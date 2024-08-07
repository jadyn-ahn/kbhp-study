package chapter3

import io.kotest.core.spec.style.FreeSpec
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
})
