package io.github.marmer

internal typealias DecimalNumber = Int
internal typealias RomanNumber = String

fun main(args: Array<String>) {
    args.forEach { arg -> println("Hello, World: $arg") }
}

private data class NumberMapping(val decimal: DecimalNumber, val roman: RomanNumber)

private val NUMBER_MAPPINGS = listOf(
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

private val FROM_DECIMAL_MAPPING = NUMBER_MAPPINGS.sortedByDescending { it.decimal }
    .map { numberMapping ->
        numberMapping.decimal to numberMapping
    }

private val FROM_ROMAN_MAPPING = NUMBER_MAPPINGS
    .map { numberMapping ->
        numberMapping.roman to numberMapping
    }

internal fun DecimalNumber.toRomanNumber(): String =
    when {
        this < 0 -> throw IllegalArgumentException("$this: is an unsupported negative value")
        this == 0 -> ""
        else -> FROM_DECIMAL_MAPPING.find { pair -> this >= pair.first }!!
            .second
            .let { it.roman + (this - it.decimal).toRomanNumber() }
    }

internal fun String.toDecimalNumber(): Int =
    FROM_ROMAN_MAPPING.find { mapping -> contains(mapping.first, ignoreCase = true) }
        .let { mapping ->
            if (mapping == null) 0
            else
                mapping.second.decimal + replaceFirst(
                    mapping.first,
                    "",
                    ignoreCase = true
                ).toDecimalNumber()
        }

