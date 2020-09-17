package io.github.marmer

internal typealias DecimalNumber = Int
internal typealias RomanNumber = String

data class DecimalRomanMapping(val decimal: DecimalNumber, val roman: RomanNumber)

val romanNumbersMappings = listOf(
    DecimalRomanMapping(1000, "M"),
    DecimalRomanMapping(900, "CM"),
    DecimalRomanMapping(500, "D"),
    DecimalRomanMapping(400, "CD"),
    DecimalRomanMapping(100, "C"),
    DecimalRomanMapping(90, "XC"),
    DecimalRomanMapping(50, "L"),
    DecimalRomanMapping(40, "XL"),
    DecimalRomanMapping(10, "X"),
    DecimalRomanMapping(9, "IX"),
    DecimalRomanMapping(5, "V"),
    DecimalRomanMapping(4, "IV"),
    DecimalRomanMapping(1, "I"),
)


fun DecimalNumber.toRomanNumber(): RomanNumber {
    if (this < 0) throw IllegalArgumentException("${this}: is an unsupported negative value")

    var remainingDecimalValue = this
    var resultingRomanValue: RomanNumber = ""

    while (remainingDecimalValue > 0) {
        romanNumbersMappings
            .find { (decimal) -> remainingDecimalValue >= decimal }
            ?.let { (decimal, roman) ->
                remainingDecimalValue -= decimal
                resultingRomanValue += roman
            }
    }

    return resultingRomanValue
}

fun RomanNumber.toDecimalNumber(): DecimalNumber {
    var remainingRomanValue = this.toUpperCase()
    var resultingDecimalValue: DecimalNumber = 0

    while (remainingRomanValue.length > 0)
        romanNumbersMappings.sortedByDescending { (decimal) -> decimal }
            .find { (_, roman) -> remainingRomanValue.startsWith(roman) }
            ?.let { (decimal, roman) ->
                remainingRomanValue = remainingRomanValue.replaceFirst(roman, "", true)
                resultingDecimalValue += decimal
            }

    return resultingDecimalValue
}
