package io.github.marmer

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class HappyNumbersKtTest {
    @Test
    @Throws(Exception::class)
    fun `isHappy - happy numbers are happy`() {
        // Preparation

        // Execution
        val result = 19.isHappy()

        // Assertion
        assertTrue(result)
    }

    @Test
    @Throws(Exception::class)
    fun `isHappy - unhappy numbers are not happy`() {
        // Preparation

        // Execution
        val result = 20.isHappy()

        // Assertion
        assertFalse(result)
    }

    @Test
    @Throws(Exception::class)
    fun `getHappyNumbersBetweenRange - get happy number range`() {
        // Preparation

        // Execution
        val result = 0 happinessTo 1000

        // Assertion
        println(result)
    }

}
