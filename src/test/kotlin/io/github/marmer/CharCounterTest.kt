package io.github.marmer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CharCounterTest {
    @Test
    @Throws(Exception::class)
    fun `countChars - Leerstring sollte leeres Dictionarey liefern`() {
        // Preparation

        // Execution
        val result = countChars("")

        // Assertion
        assertThat(result).isEmpty()
    }

    @Test
    @Throws(Exception::class)
    fun `countChars - zeichen sollten korrekt gezählt werden`() {
        // Preparation

        // Execution
        val result = countChars("AAAB")

        // Assertion
        assertThat(result).isEqualTo(
            mapOf(
                Pair('A', 3),
                Pair('B', 1)
            )
        )
    }

    @Test
    @Throws(Exception::class)
    fun `countChars - gross und Kleinschreibung werden gleichbehandelt`() {
        // Preparation

        // Execution
        val result = countChars("AaAbaA")

        // Assertion
        assertThat(result).isEqualTo(
            mapOf(
                Pair('A', 5),
                Pair('B', 1)
            )
        )
    }
}
