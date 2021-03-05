package io.github.marmer

import kotlin.math.max

fun mxdiflg(first: Array<String>, second: Array<String>): Int {
    if (first.isEmpty() || second.isEmpty()) return -1
    val firstLength = first.map { it.length }.toIntArray()
    val secondLength = second.map { it.length }.toIntArray()

    return max(
        firstLength.max()!! - secondLength.min()!!,
        secondLength.max()!! - firstLength.min()!!
    )
}
