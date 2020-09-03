package io.github.marmer

internal typealias DecimalNumber = Int
internal typealias RomanNumber = String

fun main(args: Array<String>) {
    args.forEach { arg -> println("Hello, World: $arg") }
}

private val SYMBOL_MAPPINGS = listOf(
    1000 to "M",
    500 to "D",
    100 to "C",
    50 to "L",
    10 to "X",
    5 to "V",
    1 to "I"
)

internal fun DecimalNumber.toRomanNumber(): String = when {
    this < 0 -> throw IllegalArgumentException("$this: No zeros or negative numbers allowed.")
    this == 0 -> ""
    else -> {
        val find = SYMBOL_MAPPINGS.find { pair -> this >= pair.first }!!
        find.second + (this - find.first).toRomanNumber()
    }
}
