package io.github.marmer

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import kotlin.test.assertEquals

class ApplicationTest {

    @CsvFileSource(resources = ["/morse.dict"], delimiter = ' ')
    @ParameterizedTest
    fun `Simple translation with smalest timing should be translatable`(symbol: String, morseCode: String) {
        assertEquals(symbol, translateFromMorse(morseCode))
    }

    // TODO: marmer 14.08.2021 All Single chars
    // TODO: marmer 14.08.2021 Single Word
    // TODO: marmer 14.08.2021 Multiple Words
    // TODO: marmer 14.08.2021 Timing
}
