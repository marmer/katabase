package io.github.marmer

import org.assertj.core.api.Assumptions.assumeThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import kotlin.test.assertEquals

class ApplicationTest {

    @CsvFileSource(resources = ["/morse.dict"], delimiter = ' ')
    @ParameterizedTest
    fun `Simple translation with smalest timing should be translatable`(symbol: String, morseCode: String) {
        assumeThat(symbol).isNotBlank()
        assertEquals(symbol, translateFromMorse(morseCode))
    }

    // TODO: marmer 14.08.2021 Single Word
    // TODO: marmer 14.08.2021 Multiple Words
    // TODO: marmer 14.08.2021 Timing
    // TODO: marmer 14.08.2021 Unknown code
}
