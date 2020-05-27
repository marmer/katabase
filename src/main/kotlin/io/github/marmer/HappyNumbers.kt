package io.github.marmer

infix fun Int.happinessTo(end: Int): List<Int> =
    (this..end).filter { it.isHappy() }

fun Int.isHappy(maxStepsToBecomeHappy: Int = 2000): Boolean {
    val squareSum: Int = this.toString()
        .map(Char::toString)
        .map(String::toInt)
        .map { i -> i * i }
        .toIntArray()
        .sum()

    return when {
        squareSum == 1 -> true
        maxStepsToBecomeHappy < 1 -> false
        else -> squareSum.isHappy(maxStepsToBecomeHappy - 1)
    }
}
