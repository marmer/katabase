package io.github.marmer

fun wurfeToScore(eingabe: String): Int {
    val frameBuilder = Frame.builder()
    eingabe.forEach(frameBuilder::addWurf)

    return frameBuilder.build().map(Frame::punkte).sum()
}

data class Frame(val wuerfe: ArrayList<Wurf> = ArrayList()) {
    val punkte: Int
        get() = wuerfe.sumBy(Wurf::punkte)

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var wurfContext = ArrayList<Wurf>()
        private var game = newGame()

        fun addWurf(input: Char) {
            val wurf = toWurf(input)

            wurfContext.add(wurf)
            addToCurrentFrame(wurf)

            if (gameIsReadyForNewFrame()) {
                game.add(Frame())
            }
        }

        private fun toWurf(input: Char): Wurf {
            val inEndgame = isEndspiel()
            val currentWurfIndex = wurfContext.lastIndex + 1

            return when {
                isStrike(input) -> Strike(
                    { if (inEndgame) null else wurfContext.get(currentWurfIndex + 1) },
                    { if (inEndgame) null else wurfContext.get(currentWurfIndex + 2) })

                isSpare(input) -> Spare(wurfContext.last()) {
                    if (inEndgame) null else wurfContext.get(currentWurfIndex + 1)
                }

                isPoints(input) -> Points(Character.getNumericValue(input))

                else -> Points(0)
            }
        }

        private fun gameIsReadyForNewFrame(): Boolean = !isEndspiel() &&
                (currentFrameWuerfe().size >= 2 || currentFrameWuerfe().last() is Strike)

        private fun addToCurrentFrame(wurf: Wurf) {
            val wuerfe = ArrayList(currentFrameWuerfe())
            wuerfe.add(wurf)
            game[game.lastIndex] = Frame(wuerfe)
        }

        private fun currentFrameWuerfe() = game.last().wuerfe

        private fun isEndspiel() = game.size >= 10

        private fun isPoints(wurf: Char) = wurf.isDigit()

        private fun isSpare(wurf: Char) = wurf == '/'

        private fun isStrike(wurf: Char) = wurf == 'X'

        private fun newGame() = arrayListOf(Frame())

        fun build(): ArrayList<Frame> {
            val retVal = game
            game = newGame()
            return retVal
        }
    }
}


interface Wurf {
    val basispunkte: Int
    val extrapunkte: Int
    val punkte: Int
        get() = basispunkte + extrapunkte
}

data class Points(override val basispunkte: Int) : Wurf {
    override val extrapunkte: Int = 0
}

data class Spare(val vorgaenger: Wurf, val nachfolger: (() -> Wurf?)) : Wurf {
    override val basispunkte: Int = 10 - vorgaenger.basispunkte
    override val extrapunkte: Int
        get() = nachfolger()?.basispunkte ?: 0
}

data class Strike(val nachfolger1: (() -> Wurf?), val nachfolger2: (() -> Wurf?)) : Wurf {
    override val basispunkte: Int = 10
    override val extrapunkte: Int
        get() = (nachfolger1()?.basispunkte ?: 0) +
                (nachfolger2()?.basispunkte ?: 0)

}
