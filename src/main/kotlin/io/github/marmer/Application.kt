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

private val WORD_PAUSE_ELECTRICAL_SIGNAL = "0000000"
private val DOT_DASH_PAUSE_ELECTRICAL_SIGNAL = "000"
private val DASH_ELECTRICAL_SIGNAL = "111"
private val DOT_ELECTRICAL_SIGNAL = "1"
private val ELECTRICAL_SIGNAL_PAUSE = "0"

private val WORD_PAUSE = "   "
private val DOT_DASH_PAUSE = " "
private val DASH = "-"
private val DOT = "."

private fun String.toMorse(): String {
    return replace(WORD_PAUSE_ELECTRICAL_SIGNAL.repeat(timing()), WORD_PAUSE)
        .replace(DOT_DASH_PAUSE_ELECTRICAL_SIGNAL.repeat(timing()), DOT_DASH_PAUSE)
        .replace(DASH_ELECTRICAL_SIGNAL.repeat(timing()), DASH)
        .replace(DOT_ELECTRICAL_SIGNAL.repeat(timing()), DOT)
        .replace(ELECTRICAL_SIGNAL_PAUSE.repeat(timing()), "")
}

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



