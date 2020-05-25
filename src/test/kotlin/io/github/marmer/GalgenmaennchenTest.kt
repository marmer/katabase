package io.github.marmer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GalgenmaennchenTest {
    @Test
    @Throws(Exception::class)
    fun `rateBuchstabe - sollte nur gesuchte buchstaben des gesuchten Wortes finden`() {
        // Preparation
        var underTest = Galgenmaennchen("banana")


        // Execution
        assertThat(underTest.toString()).isEqualTo("------")

        underTest = underTest.rateBuchstabe('x')
        assertThat(underTest.toString()).isEqualTo("------")

        underTest = underTest.rateBuchstabe('b')
        assertThat(underTest.toString()).isEqualTo("b-----")

        underTest = underTest.rateBuchstabe('n')
        assertThat(underTest.toString()).isEqualTo("b-n-n-")

        underTest = underTest.rateBuchstabe('a')
        assertThat(underTest.toString()).isEqualTo("banana")
    }

    @Test
    @Throws(Exception::class)
    fun `rateBuchstabe - gross und Kleinschreibung sollte egal sein`() {
        // Preparation
        var underTest = Galgenmaennchen("aABb")

        // Execution
        underTest = underTest.rateBuchstabe('b')
        assertThat(underTest.toString()).isEqualTo("--Bb")

        // Assertion
        underTest = underTest.rateBuchstabe('A')
        assertThat(underTest.toString()).isEqualTo("aABb")
    }
}
