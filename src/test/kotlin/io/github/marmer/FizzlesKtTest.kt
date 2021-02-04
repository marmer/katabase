package io.github.marmer

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Test

internal class FizzlesKtTest {
    @Test
    fun `Zahlen, welche nur durch zwei teilbar sind sollten fizzeln`() {
        // Preparation

        // Execution
        val result = ClosedRange(4, 4).toFizzeles()

        // Assertion
        assertThat(result, contains("fizz"))
    }

    @Test
    fun `Zahlen, welche nur durch drei teilbar sind sollten buzzeln`() {
        // Preparation

        // Execution
        val result = ClosedRange(9, 9).toFizzeles()

        // Assertion
        assertThat(result, contains("buzz"))
    }

    @Test
    fun `zahlen, auf die keine Bedingung zutrifft sollten als solche ausgegeben werden`() {
        // Preparation

        // Execution
        val result = ClosedRange(35, 35).toFizzeles()

        // Assertion
        assertThat(result, contains("35"))
    }

    @Test
    fun `Listen von zahlen sollten konvertierbar berechnenbar sein`() {
        // Preparation

        // Execution
        val result = ClosedRange(1, 6).toFizzeles()

        // Assertion
        assertThat(result, contains("1", "fizzprime", "buzzprime", "fizz", "prime", "fizzbuzz"))
    }

    @Test
    fun `Wenn mehrere Bedingungen auf eine zahl zutreffen, sollten sich alle Resulteate wiederfinden`() {
        // Preparation

        // Execution
        val result = ClosedRange(6, 6).toFizzeles()

        // Assertion
        assertThat(result, contains("fizzbuzz"))
    }

    @Test
    fun `primzahlen sollten mit prime gemeldet werden`() {
        // Preparation

        // Execution
        val result = ClosedRange(13, 13).toFizzeles()

        // Assertion
        assertThat(result, contains("prime"))
    }
}
