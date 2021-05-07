package io.github.marmer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `es sollte nicht umgebrochen werden, wenn die Laenge kleiner ist als die angegeben Anzahl an Zeichen`() {
        // Preparation

        // Execution
        val result = umbrechen("""Aa Bb""", 5)

        // Assertion
        assertEquals("""Aa Bb""", result)
    }

    @Test
    fun `Umbruch auf ganzes Wort bei mehr Zeichen als angegeben`() {
        // Preparation

        // Execution
        val result = umbrechen("""Aa Bb""", 3)

        // Assertion
        assertEquals(
            """
                |Aa
                |Bb""".trimMargin(), result
        )
    }

    @Test
    fun `umbrueche sollten auch bei mehreren Woertern pro Zeile möglich sein`() {
        // Preparation

        // Execution
        val result = umbrechen("""Aa Bb Cc Dd Ee""", 5)

        // Assertion
        assertEquals(
            """
                |Aa Bb
                |Cc Dd
                |Ee
            """.trimMargin(), result
        )
    }
}
