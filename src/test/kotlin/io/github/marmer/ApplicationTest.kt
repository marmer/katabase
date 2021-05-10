package io.github.marmer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `Kein Umbruch, wenn nicht weniger Zeichen als erlaubt angegeben werden`() {
        // Preparation

        // Execution
        val result = ZeilenFormatter.umbrechen("AaA Bb C", 8)

        // Assertion
        assertEquals("AaA Bb C", result)
    }

    @Test
    fun `Umbruch ganzer Woerter bei uberschreitung der maximalen Zeilenlaenge`() {
        // Preparation

        // Execution
        val result = ZeilenFormatter.umbrechen("AaA BbB CcC", 4)

        // Assertion
        assertEquals(
            """
                |AaA
                |BbB
                |CcC""".trimMargin(), result
        )
    }

    @Test
    fun `Umbrueche in Woertern sollten Woerter abschneiden`() {
        // Preparation

        // Execution
        val result = ZeilenFormatter.umbrechen("AaA BbB C", 6)

        // Assertion
        assertEquals(
            """
                |AaA Bb
                |B C""".trimMargin(), result
        )
    }

}
