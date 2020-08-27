package io.github.marmer

data class Hangman(val searchWord: String, private val maxFailedTries: Int = 7) {
    var guessedTries = emptySet<Char>()
        private set

    private constructor(searchWord: String, maxFailedTries: Int, guessedTries: Set<Char>) : this(
        searchWord,
        maxFailedTries
    ) {
        this.guessedTries = guessedTries
    }

    fun guessLetter(letter: Char): Hangman =
        if (isGameOver())
            Hangman(searchWord, maxFailedTries, guessedTries)
        else
            Hangman(searchWord, maxFailedTries, guessedTries + letter.asStandardForm())

    private fun isGameOver() = failedTries() >= maxFailedTries

    private fun failedTries(): Int = guessedTries.filterNot { searchWord.contains(it) }.size

    override fun toString(): String =
        searchWord.map { if (guessedTries.contains(it.asStandardForm())) it else '-' }.joinToString("")

    fun Char.asStandardForm() = this.toLowerCase()
}
