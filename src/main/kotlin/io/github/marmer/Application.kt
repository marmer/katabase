package io.github.marmer

import kotlin.math.min

fun main(args: Array<String>) {
    umbrechen(args[0], args[1].toInt())
}

private val SPACE_SIZE = 1

fun umbrechen(text: String, maxZeilenLaenge: Int): String =
    if (text.isBlank())
        ""
    else
        text
            .toWords()
            .toLinesWithMaxLength(maxZeilenLaenge)
            .map { it.toBlocksatz(maxZeilenLaenge) }
            .joinToString("\n")

private fun String.toBlocksatz(maxZeilenLaenge: Int): String =
    if (isFullWordLine())
        this
    else {
        val woerter = split(" ")
        val anzahlDerWoerter = woerter.size
        val anzahlFreiraeume = anzahlDerWoerter - 1
        val gesamtzahlNoetigerLeerzeichen = maxZeilenLaenge - woerter.map { it.length }.sum()
        val anzahlLeerzeichenProFreiraum = gesamtzahlNoetigerLeerzeichen / anzahlFreiraeume

        if (gesamtzahlNoetigerLeerzeichen == 0)
            this
        else {
            val smallReplacement = getStringWithGivenNumberOfSpaces(anzahlLeerzeichenProFreiraum)

            woerter.mapIndexed { index, word ->
                word +
                        if (index < gesamtzahlNoetigerLeerzeichen % anzahlFreiraeume)
                            " " //additional space
                        else
                            ""
            }.joinToString(smallReplacement)
        }
    }

private fun getStringWithGivenNumberOfSpaces(anzahlLeerzeichenProFreiraum: Int) =
    (1..anzahlLeerzeichenProFreiraum).map { " " }.joinToString("")


private fun String.isFullWordLine() = !contains(" ")

private fun List<String>.toLinesWithMaxLength(maxZeilenLaenge: Int): List<String> =
    fold(emptyList()) { lines, word ->
        if (lines.isEmpty())
            listOf(word)
        else if (lines.last().length + word.length + SPACE_SIZE > maxZeilenLaenge)
            if (word.length > maxZeilenLaenge)
                lines + word.splitToChunksWithSize(maxZeilenLaenge)
            else
                lines + word
        else {
            val lastLine = lines.last()
            lines.dropLast(SPACE_SIZE) + "$lastLine $word"
        }
    }

private fun String.splitToChunksWithSize(maxZeilenLaenge: Int): List<String> =
    if (isEmpty())
        emptyList()
    else
        listOf(
            subSequence(
                0,
                min(length, maxZeilenLaenge)
            ).toString()
        ) + drop(maxZeilenLaenge).splitToChunksWithSize(maxZeilenLaenge)

private fun String.toWords() = trim().split(Regex("\\s+"))
