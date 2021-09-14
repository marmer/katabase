package io.github.marmer

import io.github.marmer.domain.decodeMorse
import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    app(reader::readLine, ::println)
}

fun app(readInput: () -> String, writeOutput: (String) -> Unit) {
    var line = readInput()

    while (!isStopLine(line)) {
        writeOutput(decodeMorse(line))
        line = readInput()
    }
}

private fun isStopLine(line: String?) = line == "quit"
