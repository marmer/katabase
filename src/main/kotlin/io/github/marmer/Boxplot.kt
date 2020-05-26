package io.github.marmer

import kotlin.math.roundToInt

class Boxplot(vararg values: Int) {
    private val sortedValues = values.copyOf().apply { sort() }

    companion object {
        const val PLOTSIZE = 50
    }

    val median = sortedValues.getMedian()
    val minimum = sortedValues.first()
    val maximum = sortedValues.last()
    val unteresQuartil = sortedValues.copyOfUntereHaelfte().getMedian()
    val oberesQuartil = sortedValues.copyOfObereHaelfte().getMedian()
    val plot: String
        get() = (1..PLOTSIZE).joinToString("", transform = this::toPlotChar)

    private fun IntArray.getMedian() =
        if (size % 2 != 0)
            this[size / 2].toDouble()
        else
            (this[size / 2 - 1] + this[size / 2]) / 2.0

    fun IntArray.copyOfUntereHaelfte() =
        if (size <= 1) copyOf()
        else copyOfRange(0, size / 2)

    fun IntArray.copyOfObereHaelfte() =
        if (size <= 1) copyOf()
        else copyOfRange(size / 2, size)


    private fun toPlotChar(it: Int): String {
        return when {
            it.equals(1) || it.equals(PLOTSIZE) -> "|"
            it.equals(normalized(median)) -> "#"
            it in normalized(unteresQuartil)..normalized(oberesQuartil) -> "O"
            else -> "-"
        }
    }

    private fun normalized(median: Double): Int {
        return (((median - minimum) / (maximum - minimum)) * PLOTSIZE).roundToInt()
    }


}

