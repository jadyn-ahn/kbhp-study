import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Ch3MingTest : StringSpec({
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
})
