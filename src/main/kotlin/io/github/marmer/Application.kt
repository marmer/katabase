package io.github.marmer

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader

private val dict = BufferedReader(
    InputStreamReader(BufferedInputStream(object {}.javaClass.getResourceAsStream("/morse.dict")))
)
    .readLines()
    .map { it.split(" ")[1] to it.split(" ")[0] }
    .toMap()


fun translateFromMorse(morse: String): String =
    dict.get(morse) ?: throw IllegalArgumentException(morse)



