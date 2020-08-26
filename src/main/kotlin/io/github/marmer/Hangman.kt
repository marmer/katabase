package io.github.marmer

data class Hangman(val searchWord: String) {
    private var guessedTries = emptySet<Char>()

    private constructor(searchWord: String, guessedTries: Set<Char>) : this(searchWord) {
        this.guessedTries = guessedTries
    }

    fun guessLetter(letter: Char): Hangman =
        Hangman(searchWord, guessedTries + letter.asStandardForm())

    override fun toString(): String =
        searchWord.map { if (guessedTries.contains(it.asStandardForm())) it else '-' }.joinToString("")

    fun Char.asStandardForm() = this.toLowerCase()
}
