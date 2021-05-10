package io.github.marmer

fun umbrechen(text: String, maxLineLength: Int) =
    text
        .bereinigeLeerzeichen()
        .chunked(maxLineLength)
        .map { it.trim() }
        .joinToString("\n")

private fun String.bereinigeLeerzeichen() =
    replace(Regex("\\s+"), " ")
        .trim()

