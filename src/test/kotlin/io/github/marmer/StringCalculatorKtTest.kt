package io.github.marmer

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class StringCalculatorKtTest {

    @Nested
    inner class Add {
        @Test
        @Throws(Exception::class)
        fun `an empty input line should serve 0`() {
            // Preparation

            // Execution
            val result = add("")

            // Assertion
            assertEquals(0, result)
        }

        @Test
        @Throws(Exception::class)
        fun `single numbers should be returned directly`() {
            // Preparation

            // Execution
            val result = add("1000")

            // Assertion
            assertEquals(1000, result)
        }

        @Test
        @Throws(Exception::class)
        fun `the sum of two numbers should be returned`() {
            // Preparation

            // Execution
            val result = add("5,7")

            // Assertion
            assertEquals(12, result)
        }

        @Test
        @Throws(Exception::class)
        fun `It should be possible to handle an unknown amount of numbers`() {
            // Preparation

            // Execution
            val result = add("5,7,11")

            // Assertion
            assertEquals(23, result)
        }

        @Test
        @Throws(Exception::class)
        fun `newline is a valid separator as well`() {
            // Preparation

            // Execution
            val result = add("1\n2,3")

            // Assertion
            assertEquals(6, result)
        }

        @Test
        @Throws(Exception::class)
        fun `Empty number parts should be handled as zero as well`() {
            // Preparation

            // Execution
            val result = add(",2,,3,")

            // Assertion
            assertEquals(5, result)
        }

        @Test
        @Throws(Exception::class)
        fun `No negative Numbers allowed`() {
            // Preparation

            // Execution
            val result = assertThrows<IllegalArgumentException> { add("-42") }

            // Assertion
            assertEquals("No negatives allowed", result.message)
        }

        @Test
        @Throws(Exception::class)
        fun `Numbers above 1000 should be ignored`() {
            // Preparation

            // Execution
            val result = add("42,1001,8")

            // Assertion
            assertEquals(50, result)
        }

        @Test
        @Throws(Exception::class)
        fun `Custom delimiters can be chosen`() {
            // Preparation

            // Execution
            val result = add("//!\n2!3!5")

            // Assertion
            assertEquals(10, result)
        }
    }

}
