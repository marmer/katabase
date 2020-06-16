package io.github.marmer

import java.io.BufferedReader
import java.io.InputStreamReader

typealias InputProvider = () -> String
typealias OutputProcessor = (String) -> Unit

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    OhceApplication(
        inputProvider = reader::readLine,
        outputProcessor = ::println,
        greetingProvider = GreetingProvider(SystemTimeProvider())
    ).start(args[0])
}

class OhceApplication(
    private val inputProvider: InputProvider,
    private val outputProcessor: OutputProcessor,
    private val greetingProvider: GreetingProvider
) {
    fun InputProvider.forEach(callback: (String) -> Unit) {
        do {
            val currentInput = this()
            callback(currentInput)
        } while (!currentInput.isStopWord())
    }

    fun start(name: String = "anonymous") {
        outputProcessor(greetingsFor(name))

        inputProvider.forEach { originalWord ->
            val reverseWord = originalWord.reversed()

            if (originalWord.isStopWord()) {
                outputProcessor("Adios $name")
            } else {
                outputProcessor(reverseWord)
            }

            if (reverseWord.equals(originalWord))
                outputProcessor("¡Bonita palabra!")

        }
    }

    private fun greetingsFor(name: String) = greetingProvider.greetingFor(name)

    private fun String.isStopWord() = equals("Stop!")
}


