package io.github.marmer

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader

private val dict by lazy {
    BufferedReader(InputStreamReader(BufferedInputStream(object {}.javaClass.getResourceAsStream("/morse.dict"))))
        .readLines()
        .map {
            it.split(" ")[1] to it.split(" ")[0]
        }
        .toMap()
}

fun translateFromMorse(morse: String): String =
    morse.toMorseWord()
        .map(::toReadableWord)
        .joinToString(" ")

private fun toReadableWord(word: String) = word.toMorseSymbol()
    .map(::toSymbol)
    .joinToString("")

private fun String.toMorseSymbol() = split(" ")

private fun toSymbol(morseSymbol: String) = dict[morseSymbol] ?: ""

private fun String.toMorseWord() = split("   ")



