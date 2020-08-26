package io.github.marmer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class HangmanTest {
    @Test
    @Throws(Exception::class)
    fun `rateBuchstabe - sollte nur gesuchte buchstaben des gesuchten Wortes finden`() {
        // Preparation
        var underTest = Hangman("banana")


        // Execution
        assertThat(underTest.toString()).isEqualTo("------")

        underTest = underTest.guessLetter('x')
        assertThat(underTest.toString()).isEqualTo("------")

        underTest = underTest.guessLetter('b')
        assertThat(underTest.toString()).isEqualTo("b-----")

        underTest = underTest.guessLetter('n')
        assertThat(underTest.toString()).isEqualTo("b-n-n-")

        underTest = underTest.guessLetter('a')
        assertThat(underTest.toString()).isEqualTo("banana")
    }

    @Test
    @Throws(Exception::class)
    fun `rateBuchstabe - gross und Kleinschreibung sollte egal sein`() {
        // Preparation
        var underTest = Hangman("aABb")

        // Execution
        underTest = underTest.guessLetter('b')
        assertThat(underTest.toString()).isEqualTo("--Bb")

        // Assertion
        underTest = underTest.guessLetter('A')
        assertThat(underTest.toString()).isEqualTo("aABb")
    }

    @Test
    @Throws(Exception::class)
    fun `ratenBuchstabe - wenn die maximale Anzahl der Buchstaben ueberschritten ist, sind keine weiteren Eingaben mehr moeglich`() {
        // Preparation
        var underTest = Hangman("aABb", maxTries = 2)

        // Execution
        underTest = underTest.guessLetter('b')
        underTest = underTest.guessLetter('c')
        val stateAfterTwoTries = underTest.toString()

        // Assertion
        val stateAfterNotAllowedTry = underTest.guessLetter('A').toString()

        assertEquals(stateAfterTwoTries, stateAfterTwoTries, stateAfterNotAllowedTry)
    }
}
