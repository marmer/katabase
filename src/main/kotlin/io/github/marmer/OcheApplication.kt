package io.github.marmer

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.function.Consumer
import java.util.function.Supplier


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val oche = Oche(
        Supplier { reader.readLine() },
        Consumer(::println),
        GreetingProvider(SystemTimeProvider())
    )
    oche.start(args[0])
}
