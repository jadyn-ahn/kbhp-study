package chapter5.jiwon

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Chapter5Test: FreeSpec({
    "intList" {
        intList.getHead() shouldBe 1
        doubleList.getHead() shouldBe 1.0
    }
})
