package io.github.marmer

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.*
import java.util.concurrent.TimeUnit

internal class MorseApplicationKtIT {
    private var underTest = ::app

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    fun `should stop the application if the stop command has been passed`() {
        // Preparation
        val commands: Queue<String> = LinkedList(listOf("quit"))
        val results = ArrayList<String>()

        // Execution
        underTest(commands::poll, results::add)

        // Assertion
        assertThat(results).isEmpty()
    }

    @Test
    fun `should serve the translations until stop`() {
        // Preparation
        val commands: Queue<String> = LinkedList(
            listOf(
                "111010100010001011101", //Timing 1
                "1110111000111011101110001011101110100010101", //Timing 1
                "11101010001000101110100000001110111000111011101110001011101110100010101", //Timing 1
                "111111111000111000111000000000111000000000111000111111111000111", //Timing 3
                "111111111000111111111000000000111111111000111111111000111111111000000000111000111111111000111111111000111000000000111000111000111", //Timing 3
                "111111111000111000111000000000111000000000111000111111111000111000000000000000000000111111111000111111111000000000111111111000111111111000111111111000000000111000111111111000111111111000111000000000111000111000111", //Timing 3
                "quit"
            )
        )
        val results = ArrayList<String>()

        // Execution
        underTest(commands::poll, results::add)

        // Assertion
        assertThat(results).containsExactly(
            "DER", "MOPS", "DER MOPS", //Timing 1
            "DER", "MOPS", "DER MOPS" //Timing 3
        ).inOrder()
    }
}
