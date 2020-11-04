package io.github.marmer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class DiamondTest {

    @Test
    fun `first letter given`() {
        // Preparation

        // Execution
        val result = getDiamondStringFor('A')

        // Assertion
        assertEquals(
            """
                A
            """.trimIndent(),
            result
        )
    }

    @Test
    fun `small letter given`() {
        // Preparation

        // Execution
        val result = getDiamondStringFor('a')

        // Assertion
        assertEquals(
            """
                a
            """.trimIndent(),
            result
        )
    }

    @Test
    fun `second letters given`() {
        // Preparation

        // Execution
        val result = getDiamondStringFor('B')

        // Assertion
        assertEquals(
            """
                 A
                B B
                 A
            """.trimIndent(),
            result
        )
    }

    @Test
    fun `treee letters given`() {
        // Preparation

        // Execution
        val result = getDiamondStringFor('C')

        // Assertion
        assertEquals(
            """
                  A
                 B B
                C   C
                 B B
                  A
            """.trimIndent(),
            result
        )
    }

    @Test
    fun `four letters given`() {
        // Preparation

        // Execution
        val result = getDiamondStringFor('D')

        // Assertion
        assertEquals(
            """
                   A
                  B B
                 C   C
                D     D
                 C   C
                  B B
                   A
            """.trimIndent(),
            result
        )
    }

    @Test
    fun `non letters should produce illegal argument exceptions`() {
        // Preparation

        // Execution
        val exception = assertThrows<IllegalArgumentException> { getDiamondStringFor('!') }

        // Assertion
        assertEquals("only letters allowed", exception.message)
    }
}
