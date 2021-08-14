package io.github.marmer

import org.assertj.core.api.Assumptions.assumeThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import kotlin.test.assertEquals

class ApplicationTest {

    @CsvFileSource(resources = ["/morse.dict"], delimiter = ' ')
    @ParameterizedTest(name = "[{index}] \"{1}\" translates to \"{0}\"")
    fun `Simple translation with smalest timing should be translatable`(symbol: String, morseCode: String) {
        assumeThat(symbol).isNotBlank()
        assertEquals(symbol, translateFromMorse(morseCode))
    }

    @Test
    fun `Translation of single word should be possble`() {
        assertEquals("MOPS", translateFromMorse("-- --- .--. ..."))
    }

    @Test
    fun `Translation of multiple words should be possble`() {
        assertEquals("DER MOPS", translateFromMorse("-.. . .-.   -- --- .--. ..."))
    }

    @Test
    fun `Electrical Signal Translation`() {
        assertEquals(
            "DER MOPS",
            translateFromElectricalSignalMorse("11101010001000101110100000001110111000111011101110001011101110100010101")
        )
    }

    @Test
    fun `Electrical Signal Translation with timing 2`() {
        assertEquals(
            "DER MOPS",
            translateFromElectricalSignalMorse("1111110011001100000011000000110011111100110000000000000011111100111111000000111111001111110011111100000011001111110011111100110000001100110011")
        )
    }

    @Test
    fun `Electrical Signal Translation with timing 3`() {
        assertEquals(
            "DER MOPS",
            translateFromElectricalSignalMorse("111111111000111000111000000000111000000000111000111111111000111000000000000000000000111111111000111111111000000000111111111000111111111000111111111000000000111000111111111000111111111000111000000000111000111000111")
        )
    }
}
