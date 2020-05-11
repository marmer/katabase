package io.github.marmer

fun wurfeToScore(eingabe: String): Int = eingabe.mapIndexed { index, input -> toWurfpunkte(index, eingabe) }.sum()

private fun toWurfpunkte(index: Int, eingabe: String): Int = when {
    WurfTyp.isSpare(eingabe[index]) ->
        getBasispunkteFuer(index, eingabe) + getSpareExtrapunkte(index, eingabe)

    WurfTyp.isStrike(eingabe[index]) ->
        getBasispunkteFuer(index, eingabe) + getStrikeExtraPunkte(index, eingabe)

    else -> getBasispunkteFuer(index, eingabe)
}

private fun getSpareExtrapunkte(index: Int, eingabe: String) =
    if (isEndspiel(index, eingabe)) 0
    else getBasispunkteFuer(index + 1, eingabe)

private fun getStrikeExtraPunkte(index: Int, eingabe: String) =
    if (isEndspiel(index, eingabe)) 0
    else getBasispunkteFuer(index + 1, eingabe) + getBasispunkteFuer(index + 2, eingabe)

private fun isEndspiel(index: Int, eingabe: String): Boolean {
    val kleinsterMoeglicherEndspielindex = 9
    if (index < kleinsterMoeglicherEndspielindex) {
        return false
    }
    var halbeFrames = 0
    for (i in 0 until index) {
        halbeFrames += if (WurfTyp.isStrike(eingabe[i])) 2 else 1
    }
    return halbeFrames >= kleinsterMoeglicherEndspielindex * 2
}

private fun getBasispunkteFuer(index: Int, eingabe: String): Int = when {
    WurfTyp.von(eingabe[index]) == WurfTyp.STRIKE -> 10
    WurfTyp.von(eingabe[index]) == WurfTyp.SPARE -> 10 - getBasispunkteFuer(index - 1, eingabe)
    WurfTyp.von(eingabe[index]) == WurfTyp.POINTS -> Character.getNumericValue(eingabe[index])
    else -> 0
}

private enum class WurfTyp {
    STRIKE, SPARE, POINTS, ZERO;

    companion object {
        fun von(input: Char) =
            when {
                isStrike(input) -> STRIKE
                isSpare(input) -> SPARE
                input.isDigit() -> POINTS
                else -> ZERO
            }

        fun isSpare(input: Char) = input == '/'

        fun isStrike(input: Char) = input == 'X'
    }
}
