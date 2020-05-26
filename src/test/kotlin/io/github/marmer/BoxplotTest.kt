package io.github.marmer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BoxplotTest {
    @Test
    @Throws(Exception::class)
    fun `minimum - sollte minimum finden`() {
        // Preparation
        val underTest = Boxplot(2, 1, 3)

        // Execution
        val result = underTest.minimum

        // Assertion
        assertEquals(1, result)
    }

    @Test
    @Throws(Exception::class)
    fun `maximum - sollte maximum finden`() {
        // Preparation
        val underTest = Boxplot(2, 3, 1)

        // Execution
        val result = underTest.maximum

        // Assertion
        assertEquals(3, result)
    }

    @Test
    @Throws(Exception::class)
    fun `median - sollte median in sortierter gerader Liste finden`() {
        // Preparation
        val underTest = Boxplot(1, 2, 3, 5, 7, 11, 13, 17, 19, 21, 23)

        // Execution
        val result = underTest.median

        // Assertion
        assertEquals(11.0, result)
    }

    @Test
    @Throws(Exception::class)
    fun `median - sollte median in unsortierter gerader Liste finden`() {
        // Preparation
        val underTest = Boxplot(19, 23, 3, 5, 7, 17, 13, 11, 1, 21, 2)

        // Execution
        val result = underTest.median

        // Assertion
        assertEquals(11.0, result)
    }

    @Test
    @Throws(Exception::class)
    fun `median - sollte median in ungeraeder Liste finden`() {
        // Preparation
        val underTest = Boxplot(1, 2, 3, 5, 7, 11, 13, 17, 19, 21)

        // Execution
        val result = underTest.median

        // Assertion
        assertEquals(9.0, result)
    }

    @Test
    @Throws(Exception::class)
    fun `unteresQuartil - sollte unteres quartil liefern`() {
        // Preparation
        val underTest = Boxplot(1, 2, 3, 5)

        // Execution
        val result = underTest.unteresQuartil

        // Assertion
        assertEquals(1.5, result)
    }

    @Test
    @Throws(Exception::class)
    fun `oberesQuartil - sollte oberes quartil liefern`() {
        // Preparation
        val underTest = Boxplot(1, 2, 3, 5)

        // Execution
        val result = underTest.oberesQuartil

        // Assertion
        assertEquals(4.0, result)
    }

    @Test
    @Throws(Exception::class)
    fun `oberesQuartil - sollte obXXeres quartil liefern`() {
        // Preparation
        val underTest = Boxplot(4)

        // Execution
        val result = underTest.unteresQuartil

        // Assertion
        assertEquals(4.0, result)
    }

    @Test
    @Throws(Exception::class)
    fun `printPlot - sollte Plotten`() {
        // Preparation
        val underTest = Boxplot(1, 2, 3, 5, 7, 11, 13, 17, 19, 25)

        // Execution
        val result = underTest.plot

        // Assertion
        assertEquals(
            "|--OOOOOOOOOOOOO#OOOOOOOOOOOOOOOO----------------|",
            result
        )
    }
}
