package io.github.marmer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
}
