package io.github.marmer

private val dict = listOf(
    Pair("0000000", "   "),
    Pair("111", "-"),
    Pair("000", " "),
    Pair("1", "."),
    Pair("0", ""),
)

fun decodeSignalMessage(message: String): String {
    var normalizedMessage = message.normalizeTiming()
    dict.forEach { (key, value) ->
        normalizedMessage = normalizedMessage.replace(key, value)
    }
    return normalizedMessage
}

private fun String.normalizeTiming(): String {
    var normalized = this
    val timing = this.getTiming()
    dict.reversed().forEach { (key) ->
        normalized = normalized.replace(key.repeat(timing), key)
    }
    return normalized
}

private fun String.getTiming(): Int =
    this.split("1")
        .filter(String::isNotEmpty)
        .map { it.length }
        .minOf { it }

