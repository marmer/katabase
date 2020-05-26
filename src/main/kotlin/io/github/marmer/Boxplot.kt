package io.github.marmer

class Boxplot(private vararg val values: Int) {
    private val sortedValues = values.copyOf().apply { sort() }

    val median = sortedValues.getMedian()
    val minimum = sortedValues.first()
    val maximum = sortedValues.last()
    val unteresQuartil = sortedValues.copyOfUntereHaelfte().getMedian()
    val oberesQuartil = sortedValues.copyOfObereHaelfte().getMedian()
    val plot: String
        get() = (minimum..maximum)
            .map { toPlotChar(it) }
            .joinToString("")

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
            it.equals(minimum) || it.equals(maximum) -> "|"
            it.equals(median.toInt()) -> "#"
            it in unteresQuartil.toInt()..oberesQuartil.toInt() -> "O"
            else -> "-"
        }
    }

}

