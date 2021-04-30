package io.github.marmer

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.emptyString
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun `Text mit kleinerer Laenge als uebergebene Parameter muss nicht umgebrochen werden`() {
        // Preparation

        // Execution
        val result = umbrechen("tolle Welt", 10)

        // Assertion
        assertEquals("tolle Welt", result)
    }

    @Test
    fun `Text mit groesserer Laenge als der uebergebene Parameter sollte umgebrochen werden`() {
        // Preparation

        // Execution
        val result = umbrechen("tolle Welt", 9)

        // Assertion
        assertEquals(
            """
                |tolle
                |Welt
            """.trimMargin(), result
        )
    }

    @Test
    fun `Worte nach bestehenden Umbruchen sollen zusammen in eine Zeile kommen, wenn sie hinein passen`() {
        // Preparation

        // Execution
        val result = umbrechen(
            """
                |tolle
                |Welt
            """.trimMargin(), 10
        )

        // Assertion
        assertEquals("tolle Welt".trimMargin(), result)
    }

    @Test
    fun `Wo nichts gegeben wird, kommt auch nichts zurueck`() {
        // Preparation

        // Execution
        val result = umbrechen(
            """
                
                
            """.trimMargin(), 10
        )

        // Assertion
        MatcherAssert.assertThat(result, `is`(emptyString()))
    }

    @Test
    fun `Zu lange Woerter kommen in eine eigene Zeile und werden dort umgebrochen`() {
        // Preparation

        // Execution
        val result = umbrechen(
            """
                |tolle
                |HundekuchenHundekuchen
                |blub Welt
            """.trimMargin(), 10
        )

        // Assertion
        assertEquals(
            """
                |tolle
                |Hundekuche
                |nHundekuch
                |en    blub
                |Welt
            """.trimMargin(), result
        )
    }

    @Test
    fun `blocksatz Auffuellung funktioniert auch fuer ungleichmaessige Auffuellung`() {
        // Preparation

        // Execution
        val result = umbrechen(
            "x x x x".trimMargin(), 12
        )

        // Assertion
        assertEquals(
            "x   x   x  x", result
        )
    }

    @Test
    fun `Akzeptanztest`() {
        // Preparation

        // Execution
        val result = umbrechen(
            """
            Es blaut die Nacht,
            die Sternlein blinken,
            Schneeflöcklein leis hernieder sinken.
            """.trimMargin(), 14
        )

        // Assertion
        assertEquals(
            """
            Es  blaut  die
            Nacht,     die
            Sternlein
            blinken,
            Schneeflöcklei
            n         leis
            hernieder
            sinken.
        """.trimIndent(), result
        )
    }
}
