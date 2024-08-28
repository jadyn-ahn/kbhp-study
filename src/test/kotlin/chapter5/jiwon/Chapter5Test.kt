package chapter5.jiwon

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Chapter5Test: FreeSpec({
    "intList" {
        intList.getHead() shouldBe 1
        doubleList.getHead() shouldBe 1.0
    }

    "drop" {
        intList.drop(1).getHead() shouldBe 2
        intList.getHead() shouldBe 1
    }

    "dropWhile" {
        intList.dropWhile { it == 3 }.getHead() shouldBe 4
        intList.getHead() shouldBe 1
    }

    "indexedMap" {
        val res = intList.indexedMap { i, t -> i + t }
        res.getHead() shouldBe 1
        res.getTail().getHead() shouldBe 3
    }

    "maximumByFoldLeft" {
        intList.maximumByFoldLeft() shouldBe 4
    }
})
