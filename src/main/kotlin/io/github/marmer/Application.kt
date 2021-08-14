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

fun translateFromElectricalSignalMorse(electricalMorse: String): String =
    translateFromMorse(electricalMorse.toMorse())

private fun String.toMorse(): String =
    replace("0000000".repeat(timing()), "   ")
        .replace("000".repeat(timing()), " ")
        .replace("111".repeat(timing()), "-")
        .replace("1".repeat(timing()), ".")
        .replace("0".repeat(timing()), "")

private fun String.timing() = split(Regex("1+"))
    .map { it.length }
    .filter { it > 0 }
    .minOrNull() ?: throw IllegalArgumentException("Not able to determine timing")

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



