package io.github.marmer


typealias IDictionary = Map<Char, Int>

fun countChars(input: String): IDictionary = input
    .map { it.toUpperCase() }
    .groupingBy { it }
    .eachCount()


