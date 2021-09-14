package io.github.marmer

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class SignalDecoderKtTest {
    private var underTest: (String) -> String = ::decodeSignalMessage

    @Test
    fun `Should be able to detect a whole symbol`() {
        // Preparation

        // Execution
        val result = underTest("11101010111")

        // Assertion
        assertThat(result).isEqualTo("-..-")
    }

    @Test
    fun `Should be able to detect a whole word`() {
        // Preparation

        // Execution
        val result = underTest("1110101011100010111011101")

        // Assertion
        assertThat(result).isEqualTo("-..- .--.")
    }

    @Test
    fun `Should be able to detect multiple words`() {
        // Preparation

        // Execution
        val result = underTest("111010101110001011101110100000001011101110100011101010111")

        // Assertion
        assertThat(result).isEqualTo("-..- .--.   .--. -..-")
    }

    @Test
    fun `Should be able to handle a timing of 3`() {
        // Preparation

        // Execution
        val result =
            underTest("111111111000111000111000111111111000000000111000111111111000111111111000111000000000000000000000111000111111111000111111111000111000000000111111111000111000111000111111111")

        // Assertion
        assertThat(result).isEqualTo("-..- .--.   .--. -..-")
    }

    @Test
    fun `Should be able to handle a timing of 5`() {
        // Preparation

        // Execution
        val result =
            underTest("111111111111111000001111100000111110000011111111111111100000000000000011111000001111111111111110000011111111111111100000111110000000000000000000000000000000000011111000001111111111111110000011111111111111100000111110000000000000001111111111111110000011111000001111100000111111111111111")

        // Assertion
        assertThat(result).isEqualTo("-..- .--.   .--. -..-")
    }
}
