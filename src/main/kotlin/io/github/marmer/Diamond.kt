package io.github.marmer

fun getDiamondStringFor(letter: Char) =
    if (!letter.isLetter()) throw IllegalArgumentException("only letters allowed")
    else with(toFullDiamond(letter.toUpperCase())) {
        if (letter.isUpperCase())
            this
        else
            this.toLowerCase()
    }

private fun toFullDiamond(stopLetter: Char) =
    (upperDiamandTo(stopLetter) + lowerDiamondTo(stopLetter))
        .joinToString("\n")

private fun lowerDiamondTo(stopLetter: Char) = upperDiamandTo(stopLetter).reversed().drop(1)

private fun upperDiamandTo(stopLetter: Char) =
    upperLeftDiamondTo(stopLetter)
        .map { line -> line + line.reversed().substring(1).trimEnd() }

private fun upperLeftDiamondTo(stopLetter: Char) =
    ('A'..stopLetter).map { currentLetter ->
        "$currentLetter"
            .padStart(stopLetter - currentLetter + 1, ' ')
            .padEnd(stopLetter - currentLetter + 1 + (currentLetter - 'A'))
    }
