package io.github.marmer

import java.util.regex.Pattern

fun add(inputLine: String): Int {
    val structuredInput = structuredInput(inputLine)

    val content = structuredInput.content
    val seperator = structuredInput.seperator

    return content.split(Pattern.compile("[${seperator}\n]"))
        .map {
            if (it.isBlank()) 0
            else it.toNumber()
        }
        .sum()
}

private fun structuredInput(contentToParse: String) =
    object {
        private val hasDelimiter = contentToParse.startsWith("//")
        private val lines = contentToParse.lines()
        val seperator: String = if (hasDelimiter) lines[0].replace("//", "") else ","
        val content: String = if (hasDelimiter) lines[1] else contentToParse
    }

fun String.toNumber() =
    if (this.startsWith("-")) throw (IllegalArgumentException("No negatives allowed"))
    else if (Integer.valueOf(this) > 1000) 0
    else Integer.valueOf(this)
