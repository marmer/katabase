package io.github.marmer

internal typealias DecimalNumber = Int
internal typealias RomanNumber = String

private data class NumberMapping(val decimal: DecimalNumber, val roman: RomanNumber)

private val NUMBER_MAPPINGS = arrayOf(
    NumberMapping(decimal = 900, roman = "CM"),
    NumberMapping(decimal = 1000, roman = "M"),
    NumberMapping(decimal = 400, roman = "CD"),
    NumberMapping(decimal = 500, roman = "D"),
    NumberMapping(decimal = 90, roman = "XC"),
    NumberMapping(decimal = 100, roman = "C"),
    NumberMapping(decimal = 40, roman = "XL"),
    NumberMapping(decimal = 50, roman = "L"),
    NumberMapping(decimal = 9, roman = "IX"),
    NumberMapping(decimal = 10, roman = "X"),
    NumberMapping(decimal = 4, roman = "IV"),
    NumberMapping(decimal = 5, roman = "V"),
    NumberMapping(decimal = 1, roman = "I"),
)

fun DecimalNumber.toRomanNumber(): RomanNumber = NUMBER_MAPPINGS
    .sortedByDescending { (decimal) -> decimal }
    .find { (decimal) -> this >= decimal }
    ?.let { (decimal, roman) ->
        roman + (this - decimal).toRomanNumber()
    } ?: "" // Break condition

fun RomanNumber.toDecimalNumber(): DecimalNumber = NUMBER_MAPPINGS
    .find { (_, roman) -> contains(roman, ignoreCase = true) }
    ?.let { (decimal, roman) ->
        decimal + replaceFirst(roman, "", ignoreCase = true).toDecimalNumber()
    } ?: 0 //Break condition
