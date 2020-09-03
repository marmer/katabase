package io.github.marmer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows

internal class RomanNumberTest {
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
            assertEquals(roman, decimal.toRomanNumber())
        }, dynamicTest("$roman should be mapped to $decimal") {
            assertEquals(decimal, roman.toDecimalNumber())
        })
    }.flatMap { it }


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
            assertEquals(roman, decimal.toRomanNumber())
        }, dynamicTest("$roman should be mapped to $decimal") {
            assertEquals(decimal, roman.toDecimalNumber())
        })
    }.flatMap { it }

    @TestFactory
    fun simpleSubtractionRule() = listOf(
        4 to "IV",
        9 to "IX",
        999 to "CMXCIX",
    ).map { (decimal: DecimalNumber, roman: RomanNumber) ->
        listOf(dynamicTest("$decimal should be mapped to $roman") {
            assertEquals(roman, decimal.toRomanNumber())
        }, dynamicTest("$roman should be mapped to $decimal") {
            assertEquals(decimal, roman.toDecimalNumber())
        })
    }.flatMap { it }

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
            assertEquals(roman, decimal.toRomanNumber())
        }, dynamicTest("$roman should be mapped to $decimal") {
            assertEquals(decimal, roman.toDecimalNumber())
        }, dynamicTest("$roman should be mapped to $decimal") {
            assertEquals(decimal, roman.toLowerCase().toDecimalNumber())
        })
    }.flatMap { it }

    @Test
    @Throws(Exception::class)
    fun `negative numbers cannot be converted`() {
        // Preparation

        // Execution
        val thrownException = assertThrows<IllegalArgumentException> { (-1).toRomanNumber() }

        // Assertion
        assertEquals("-1: is an unsupported negative value", thrownException.message)

    }
}
