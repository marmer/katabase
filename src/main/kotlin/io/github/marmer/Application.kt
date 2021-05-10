package io.github.marmer

fun umbrechen(text: String, maxLineLength: Int) =
    text
        .chunked(maxLineLength)
        .map { it.trim() }
        .joinToString("\n")
