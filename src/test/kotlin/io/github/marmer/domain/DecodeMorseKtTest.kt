package io.github.marmer.domain

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class DecodeMorseKtTest {
    private var underTest: (String) -> String = ::decodeMorse

    @Test
    fun `should be able to decode letter q`() {
        // Preparation

        // Execution
        val result = underTest("--.-")

        // Assertion
        assertThat(result).isEqualTo("Q")
    }

    @Test
    fun `should be able to decode letter X`() {
        // Preparation

        // Execution
        val result = underTest("-..-")

        // Assertion
        assertThat(result).isEqualTo("X")
    }

    @Test
    fun `Should be able to translate the word 'Hello'`() {
        // Preparation

        // Execution
        val result = underTest(".... . .-.. .-.. ---")

        // Assertion
        assertThat(result).isEqualTo("HELLO")
    }

    @Test
    fun `Should be able to translate multiple words 'Hello World'`() {
        // Preparation

        // Execution
        val result = underTest(".... . .-.. .-.. ---   .-- --- .-. .-.. -..")

        // Assertion
        assertThat(result).isEqualTo("HELLO WORLD")
    }

    @Test
    fun `Unknown Letters should be translated into '?'`() {
        // Preparation

        // Execution
        val result = underTest("...-..-.-.")

        // Assertion
        assertThat(result).isEqualTo("?")
    }

}
