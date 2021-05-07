package io.github.marmer

fun umbrechen(text: String, maxLineLength: Int): String {
    return text.split(" ")
        .fold(listOf(emptyList())) { acc: List<List<String>>, currentWord: String ->
            list(acc, currentWord, maxLineLength)
        }
        .map { it.joinToString(" ") }
        .joinToString("\n")
}

private fun list(
    currentLines: List<List<String>>,
    currentWord: String,
    maxLineLength: Int
): List<List<String>> {
    val currentLastLineWords: List<String> = currentLines.last()
    val potentialNewLastLineWords: List<String> = currentLastLineWords + currentWord
    val potentialNewLineLength: Int = potentialNewLastLineWords.joinToString(" ").length

    return if (potentialNewLineLength > maxLineLength) {
        currentLines.plus<List<String>>(listOf(currentWord))
    } else {
        currentLines.dropLast(1).plus<List<String>>(potentialNewLastLineWords)
    }
}
