package chapter3

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*

class JaegyunKtTest : StringSpec({

    "2의 4승은 16" {
        power(2.0, 4) shouldBe 16
    }

    "3의 0승은 1" {
        power(3.0, 0) shouldBe 1
    }

    "-4의 3승은 -64" {
        power(-4.0, 3) shouldBe -64
    }
})