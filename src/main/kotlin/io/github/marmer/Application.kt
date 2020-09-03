package io.github.marmer

internal typealias DecimalNumber = Int
internal typealias RomanNumber = String

fun main(args: Array<String>) {
    args.forEach { arg -> println("Hello, World: $arg") }
}

private data class NumberMapping(val decimal: DecimalNumber, val roman: RomanNumber)

private data class ListNode<T>(val value: T, val next: T? = null, val prev: T? = null)

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

private val FROM_DECIMAL_MAPPING = NUMBER_MAPPINGS.mapIndexed { index, numberMapping ->
    numberMapping.decimal to ListNode(
        numberMapping,
        if (index == 0) null else NUMBER_MAPPINGS[index - 1],
        if (index == NUMBER_MAPPINGS.lastIndex) null else NUMBER_MAPPINGS[index + 1]
    )
}

private val FROM_ROMAN_MAPPING = NUMBER_MAPPINGS.mapIndexed { index, numberMapping ->
    numberMapping.roman to ListNode(
        numberMapping,
        if (index == 0) null else NUMBER_MAPPINGS[index - 1],
        if (index == NUMBER_MAPPINGS.lastIndex) null else NUMBER_MAPPINGS[index + 1]
    )
}

internal fun DecimalNumber.toRomanNumber(): String = when {
    this < 0 -> throw IllegalArgumentException("$this: No zeros or negative numbers allowed.")
    this == 0 -> ""
    else -> {
        val pair = FROM_DECIMAL_MAPPING.find { pair -> this >= pair.first }!!

        pair.second.value.roman + (this - pair.first).toRomanNumber()
    }
}

