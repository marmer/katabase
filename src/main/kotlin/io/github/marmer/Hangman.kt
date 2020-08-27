package io.github.marmer

data class Hangman(val searchWord: String, private val maxFailedTries: Int = 7) {
    var guessedTries = emptySet<Char>()
        private set

    private val numberOfFailedTries: Int
        get() = guessedTries.filterNot { searchWord.contains(it) }.size

    private val isGameOver: Boolean
        get() = numberOfFailedTries >= maxFailedTries

    private constructor(searchWord: String, maxFailedTries: Int, guessedTries: Set<Char>) : this(
        searchWord,
        maxFailedTries
    ) {
        this.guessedTries = guessedTries
    }

    fun guessLetter(letter: Char): Hangman =
        if (isGameOver)
            Hangman(searchWord, maxFailedTries, guessedTries)
        else
            Hangman(searchWord, maxFailedTries, guessedTries + letter.asStandardForm())


    override fun toString(): String =
        searchWord.map { if (guessedTries.contains(it.asStandardForm())) it else '-' }.joinToString("")

    private fun Char.asStandardForm() = this.toLowerCase()
}
