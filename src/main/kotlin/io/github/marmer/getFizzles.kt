package io.github.marmer

import java.util.stream.IntStream
import java.util.stream.Stream
import kotlin.streams.toList

fun getFizzles(from: Int, to: Int = from): List<String> {
    return IntStream.rangeClosed(from, to)
        .toFizzeled()
        .toList()
}

fun IntStream.toFizzeled(): Stream<String> {
    return mapToObj(::Fizzled)
        .map(Fizzled::fizzleValue)
}

data class Fizzled(val number: Int) {
    val fizzleValue: String
        get() = number.toFizzeled()

    private val fizzleMap: Map<Int, () -> String> = mapOf(
        2 to ({ "fuzz" }),
        3 to ({ "fizz" }),
        5 to ({ "buzz" }),
        7 to ({ "pop" })
    )

    fun Int.toFizzeled(): String {
        val specialFields = fizzleMap
            .filterKeys { this % it == 0 }

        return if (specialFields.isEmpty())
            toString()
        else
            specialFields
                .map { it.value() }
                .joinToString(separator = "")
    }
}
