package io.github.marmer

import io.github.marmer.JavaRomanNumerals.toDecimalNumber
import io.github.marmer.JavaRomanNumerals.toRomanNumber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

internal class JavaRomanNumberTest {
    @TestFactory
    fun simpleNumbers() = listOf(
        1 to "I",
        5 to "V",
        10 to "X",
        50 to "L",
        100 to "C",
        500 to "D",
        1000 to "M",
    ).map { (decimal: DecimalNumber, roman: RomanNumber) ->
        listOf(dynamicTest("$decimal should be mapped to $roman") {
            assertEquals(roman, toRomanNumber(decimal))
        }, dynamicTest("$roman should be mapped to $decimal") {
            assertEquals(decimal, toDecimalNumber(roman))
        })
    }.flatten()


    @TestFactory
    fun simpleAdditionRule() = listOf(
        3 to "III",
        15 to "XV",
        30 to "XXX",
        150 to "CL",
        300 to "CCC",
        1500 to "MD",
        3000 to "MMM",
    ).map { (decimal: DecimalNumber, roman: RomanNumber) ->
        listOf(dynamicTest("$decimal should be mapped to $roman") {
            assertEquals(roman, toRomanNumber(decimal))
        }, dynamicTest("$roman should be mapped to $decimal") {
            assertEquals(decimal, toDecimalNumber(roman))
        })
    }.flatten()

    @TestFactory
    fun simpleSubtractionRule() = listOf(
        4 to "IV",
        9 to "IX",
        999 to "CMXCIX",
    ).map { (decimal: DecimalNumber, roman: RomanNumber) ->
        listOf(dynamicTest("$decimal should be mapped to $roman") {
            assertEquals(roman, toRomanNumber(decimal))
        }, dynamicTest("$roman should be mapped to $decimal") {
            assertEquals(decimal, toDecimalNumber(roman))
        })
    }.flatten()

    @TestFactory
    fun acceptance() = listOf(
        1 to "I",
        2 to "II",
        4 to "IV",
        5 to "V",
        9 to "IX",
        10 to "X",
        42 to "XLII",
        89 to "LXXXIX",
        99 to "XCIX",
        2013 to "MMXIII",
    ).map { (decimal: DecimalNumber, roman: RomanNumber) ->
        listOf(dynamicTest("$decimal should be mapped to $roman") {
            assertEquals(roman, toRomanNumber(decimal))
        }, dynamicTest("$roman should be mapped to $decimal") {
            assertEquals(decimal, toDecimalNumber(roman))
        }, dynamicTest("$roman should be mapped to $decimal") {
            assertEquals(decimal, roman.toLowerCase().toDecimalNumber())
        })
    }.flatten()

    @TestFactory
    fun `bidirectional conversion`() = (0..3999)
        .map {
            dynamicTest(
                "$it should not change if you convert it in two directions"
            ) { assertEquals(it, toDecimalNumber(toRomanNumber(it))) }
        }
}
