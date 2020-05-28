package io.github.marmer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LifeKtIT {

    @Test
    @Throws(Exception::class)
    fun `evolve - dead cell should become alive with enough neighbours`() {
        // Preparation
        val underTest = Life(
            """........
....*...
...**...
........"""
        )

        // Execution
        val result = underTest.evolve()

        // Assertion
        assertEquals(
            Life(
                """........
...**...
...**...
........"""
            ), result
        )
    }

    @Test
    @Throws(Exception::class)
    fun `testMethodName - living cell should die with to many neighbours`() {
        // Preparation
        val underTest = Life(
            """........
..***...
...**...
........"""
        )

        // Execution
        val result = underTest.evolve()

        // Assertion
        assertEquals(
            Life(
                """...*....
..*.*...
..*.*...
........"""
            ), result
        )
    }
}
