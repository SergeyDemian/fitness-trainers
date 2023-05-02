import io.kotest.core.spec.style.ShouldSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Autotest : ShouldSpec({
    withData(
        mapOf(
            "hello_world" to Triple("Hello", " world", "Hello world"),
            "one_two" to Triple("one", " two", "one two")
        )
    ) { (f, s, r) -> r shouldBe f + s }
})