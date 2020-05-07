package io.github.marmer

fun wurfeToScore(eingabe: String): Int {
    return eingabe.mapIndexed { index, input -> toWurfpunkte(index, eingabe) }.sum()
}

private fun toWurfpunkte(index: Int, eingabe: String): Int = when {
    WurfTyp.von(eingabe[index]) == WurfTyp.SPARE ->
        getBasispunkteFuer(eingabe, index) + getAsBonuspunkt(eingabe, index + 1)

    WurfTyp.von(eingabe[index]) == WurfTyp.STRIKE ->
        getBasispunkteFuer(eingabe, index) + getAsBonuspunkt(eingabe, index + 1) + getAsBonuspunkt(eingabe, index + 2)

    else -> getBasispunkteFuer(eingabe, index)
}

fun getAsBonuspunkt(eingabe: String, index: Int): Int = when {
    isEndspiel(eingabe, index) -> 0
    else -> getBasispunkteFuer(eingabe, index)
}

fun isEndspiel(eingabe: String, index: Int): Boolean {
    if (index < eingabe.length + 2)
        return false
    else
        return true
}

private fun getBasispunkteFuer(eingabe: String, index: Int): Int = when {
    WurfTyp.von(eingabe[index]) == WurfTyp.SPARE -> 10 - getBasispunkteFuer(eingabe, index - 1)
    WurfTyp.von(eingabe[index]) == WurfTyp.STRIKE -> 10
    WurfTyp.von(eingabe[index]) == WurfTyp.POINTS -> Character.getNumericValue(eingabe[index])
    else -> 0
}

enum class WurfTyp {
    STRIKE, SPARE, POINTS, ZERO;

    companion object {
        fun von(input: Char) =
            when {
                input == 'X' -> STRIKE
                input == '/' -> STRIKE
                input.isDigit() -> POINTS
                else -> ZERO
            }
    }
}
