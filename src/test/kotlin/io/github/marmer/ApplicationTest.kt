package io.github.marmer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `Kein Umbruch, wenn nicht weniger Zeichen als erlaubt angegeben werden`() {
        // Preparation

        // Execution
        val result = umbrechen("AaA Bb C", 8)

        // Assertion
        assertEquals("AaA Bb C", result)
    }

    @Test
    fun `Umbruch ganzer Woerter bei uberschreitung der maximalen Zeilenlaenge`() {
        // Preparation

        // Execution
        val result = umbrechen("AaA BbB CcC", 4)

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
        val result = umbrechen("AaA BbB C", 6)

        // Assertion
        assertEquals(
            """
                |AaA Bb
                |B C""".trimMargin(), result
        )
    }

    @Test
    fun `Leere Texte sollten leer und unumgebrochen bleiben`() {
        // Preparation

        // Execution
        val result = umbrechen("      ", 2)

        // Assertion
        assertEquals(
            "".trimMargin(), result
        )
    }

    @Test
    fun `Leerzeichen am sollten nur soweit wie noetig beruecksichtigt werden`() {
        // Preparation

        // Execution
        val result = umbrechen(
            "    \n  AaA   \n \n    \t \n Bb C   \n\n".trimIndent(), 8
        )

        // Assertion
        assertEquals("AaA Bb C", result)
    }
}
