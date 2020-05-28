package io.github.marmer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GameOfLifeKtIT{
    @Test
    @Throws(Exception::class)
    fun `evolve - an evolution without any cells should be impossible`() {
        // Preparation

        // Execution
        val result = evolve(
            """.....
....
.**.
..*.
...."""
        )

        // Assertion
        assertEquals(
            """.....
....
.**.
.**.
....""",
            result
        )
    }
}
