package io.github.marmer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

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
    fun `guessLetter - wenn die maximale Anzahl falscher Eingaben ueberschritten ist, sind keine weiteren Eingaben mehr moeglich`() {
        // Preparation
        var underTest = Hangman("aABb", 2)

        // Execution
        underTest = underTest.guessLetter('b')
        val stateAfterTwoTries = underTest.toString()
        underTest = underTest.guessLetter('c')
        underTest = underTest.guessLetter('d')

        // Assertion
        val stateAfterNotAllowedTry = underTest.guessLetter('A').toString()

        assertEquals(stateAfterTwoTries, stateAfterNotAllowedTry)
    }

    @Test
    @Throws(Exception::class)
    fun `rateBuchstabe - solange die maximale Anzahl korrekter Eingaben unterschritten ist, sind Eingaben moeglich`() {
        // Preparation
        var underTest = Hangman("aABb", 2)

        // Execution
        underTest = underTest.guessLetter('b')
        val stateAfterTwoTries = underTest.toString()
        underTest = underTest.guessLetter('c')

        // Assertion
        val stateAfterNotAllowedTry = underTest.guessLetter('A').toString()

        assertNotEquals(stateAfterTwoTries, stateAfterNotAllowedTry)
    }
}
