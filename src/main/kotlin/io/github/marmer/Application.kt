package io.github.marmer

fun umbrechen(text: String, maxLineLength: Int) =
    text
        .replace(Regex("\\s+"), " ")
        .trim()
        .chunked(maxLineLength)
        .map { it.trim() }
        .joinToString("\n")
