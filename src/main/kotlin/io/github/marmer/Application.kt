package io.github.marmer

internal typealias DecimalNumber = Int
internal typealias RomanNumber = String

fun main(args: Array<String>) {
    args.forEach { arg -> println("Hello, World: $arg") }
}

private data class NumberMapping(val decimal: DecimalNumber, val roman: RomanNumber)

private val NUMBER_MAPPINGS = listOf(
    NumberMapping(1000, "M"),
    NumberMapping(900, "CM"),
    NumberMapping(500, "D"),
    NumberMapping(400, "CD"),
    NumberMapping(100, "C"),
    NumberMapping(90, "XC"),
    NumberMapping(50, "L"),
    NumberMapping(40, "XL"),
    NumberMapping(10, "X"),
    NumberMapping(9, "IX"),
    NumberMapping(5, "V"),
    NumberMapping(4, "IV"),
    NumberMapping(1, "I"),
)

private val FROM_DECIMAL_MAPPING = NUMBER_MAPPINGS.map { numberMapping ->
    numberMapping.decimal to numberMapping
}

internal fun DecimalNumber.toRomanNumber(): String =
    when {
        this < 0 -> throw IllegalArgumentException("$this: is an unsupported negative value")
        this == 0 -> ""
        else -> FROM_DECIMAL_MAPPING.find { pair -> this >= pair.first }!!
            .second
            .let { it.roman + (this - it.decimal).toRomanNumber() }
    }

