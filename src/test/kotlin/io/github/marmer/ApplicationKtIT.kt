package io.github.marmer

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class ApplicationKtIT {
    private var underTest = ::app

    @Test
//    @Timeout(value = 1, unit = SECONDS)
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
        val commands: Queue<String> = LinkedList(listOf("Der", "Mops", "Der Mops", "quit"))
        val results = ArrayList<String>()

        // Execution
        underTest(commands::poll, results::add)

        // Assertion
        assertThat(results).containsExactly(
            "-.. . .-.",
            "-- --- .--. ...",
            "-.. . .-.   -- --- .--. ..."
        ).inOrder()
    }
}
