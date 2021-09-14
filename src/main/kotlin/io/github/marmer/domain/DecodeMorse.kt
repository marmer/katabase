package io.github.marmer.domain

private val dict = mapOf(
    Pair(".-", "A"),
    Pair("-...", "B"),
    Pair("-.-.", "C"),
    Pair("-..", "D"),
    Pair(".", "E"),
    Pair("..-.", "F"),
    Pair("--.", "G"),
    Pair("....", "H"),
    Pair("..", "I"),
    Pair(".---", "J"),
    Pair("-.-", "K"),
    Pair(".-..", "L"),
    Pair("--", "M"),
    Pair("-.", "N"),
    Pair("---", "O"),
    Pair(".--.", "P"),
    Pair("--.-", "Q"),
    Pair(".-.", "R"),
    Pair("...", "S"),
    Pair("-", "T"),
    Pair("..-", "U"),
    Pair("...-", "V"),
    Pair(".--", "W"),
    Pair("-..-", "X"),
    Pair("-.--", "Y"),
    Pair("--..", "Z"),
    Pair("-----", "0"),
    Pair(".----", "1"),
    Pair("..---", "2"),
    Pair("...--", "3"),
    Pair("....-", "4"),
    Pair(".....", "5"),
    Pair("-....", "6"),
    Pair("--...", "7"),
    Pair("---..", "8"),
    Pair("----.", "9"),
    Pair(".-.-.-", "."),
    Pair("--..--", ","),
    Pair("..--..", "?"),
    Pair(".----.", "'"),
    Pair("-.-.--", "!"),
    Pair("-..-.", "/"),
    Pair("-.--.", "("),
    Pair("-.--.-", ")"),
    Pair(".-...", "&"),
    Pair("---...", ":"),
    Pair("-.-.-.", ";"),
    Pair("-...-", "="),
    Pair(".-.-.", "+"),
    Pair("-....-", "-"),
    Pair("..--.-", "_"),
    Pair("...-..-", "$"),
    Pair(".--.-.", "@"),
)

fun decodeMorse(line: String): String {
    return line.splitByWords().map {
        it.splitBySymbols()
            .map { dict[it] ?: "?" }
            .joinToString("")
    }
        .joinToString(" ")
}

private fun String.splitBySymbols() = split(" ")

private fun String.splitByWords() = split("   ")

