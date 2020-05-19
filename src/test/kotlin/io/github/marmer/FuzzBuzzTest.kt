package io.github.marmer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FizzBuzzTest {
    @Test
    @Throws(Exception::class)
    fun `getFizzles - durch 3 teilbare Zahlen sollten "fizz" ergeben`() {
        // Preparation

        // Execution
        val result = getFizzles(from = 6, to = 6)

        // Assertion
        assertThat(result).hasSize(1).first().asString().contains("fizz")
    }

    @Test
    @Throws(Exception::class)
    fun `getFizzles - zahlen ohne Sonderbehandlung sollten sich nicht veraendern`() {
        // Preparation

        // Execution
        val result = getFizzles(from = 19, to = 19)

        // Assertion
        assertThat(result).hasSize(1).first().asString().isEqualTo("19")
    }

    @Test
    @Throws(Exception::class)
    fun `getFizzles - durch fuenf Teilbare Zahlen sollten buzz ergeben`() {
        // Preparation

        // Execution
        val result = getFizzles(25, 25)

        // Assertion
        assertThat(result).hasSize(1).first().asString().contains("buzz")
    }

    @Test
    @Throws(Exception::class)
    fun `getFizzles(15,15 - durch mehrere sonderbehandlungen Teilbare Zahlen sollten alle Worte der Sonderbehandlung beinhalten`() {
        // Preparation

        // Execution
        val result = getFizzles(15, 15)

        // Assertion
        assertThat(result).hasSize(1).first().asString().contains("fizzbuzz")
    }

    @Test
    @Throws(Exception::class)
    fun `getFizzles - durch sieben teilbares sollte durch pop ergaenzt werden`() {
        // Preparation

        // Execution
        val result = getFizzles(14, 14)

        // Assertion
        assertThat(result).hasSize(1).first().asString().contains("pop")
    }

    @Test
    @Throws(Exception::class)
    fun `getFizzles - Akzeptanztest`() {
        // Preparation

        // Execution
        val result = getFizzles(1, 21)

        // Assertion
        assertThat(result).containsExactly(
            "1",
            "fuzz",
            "fizz",
            "fuzz",
            "buzz",
            "fuzzfizz",
            "pop",
            "fuzz",
            "fizz",
            "fuzzbuzz",
            "11",
            "fuzzfizz",
            "13",
            "fuzzpop",
            "fizzbuzz",
            "fuzz",
            "17",
            "fuzzfizz",
            "19",
            "fuzzbuzz",
            "fizzpop"
        )
    }
}


