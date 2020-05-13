package io.github.marmer

fun wurfeToScore(eingabe: String): Int {
    val frameBuilder = Frame.builder()
    eingabe.forEach(frameBuilder::addWurf)

    return frameBuilder.build().map(Frame::punkte).sum()
}

data class Frame(val wuerfe: ArrayList<Wurf> = ArrayList()) {
    val punkte: Int = wuerfe.sumBy(Wurf::punkte)

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var wurfContext = ArrayList<Wurf>()
        private var frames = newGame()

        fun addWurf(input: Char) {
            val wurf = toWurf(input)

            wurfContext.add(wurf)
            addToLastFrame(wurf)

            if (isTimeForNewFrame()) {
                frames.add(Frame())
            }

        }

        private fun isTimeForNewFrame(): Boolean =
            if (isEndspiel()) {
                false
            } else {
                lastFrameWuerfe().size >= 2 ||
                        lastFrameWuerfe().last() is Strike
            }

        private fun isEndspiel() = frames.size < 10

        private fun addToLastFrame(wurf: Wurf) {
            val wuerfe = ArrayList(lastFrameWuerfe())
            wuerfe.add(wurf)
            frames[frames.lastIndex] = Frame(wuerfe)
        }

        private fun lastFrameWuerfe() = frames.last().wuerfe

        private fun isEndgame(): Boolean = frames.size >= 10

        private fun toWurf(input: Char): Wurf {
            val inEndgame = isEndgame()
            val currentWurfIndex = wurfContext.lastIndex

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

        private fun isPoints(wurf: Char) = wurf.isDigit()

        private fun isSpare(wurf: Char) = wurf == '/'

        private fun isStrike(wurf: Char) = wurf == 'X'

        fun build(): ArrayList<Frame> {
            val retVal = frames
            frames = newGame()
            return retVal
        }

        private fun newGame() = arrayListOf(Frame())
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

data class Spare(val vorgaenger: Wurf, val nachfolger: () -> Wurf?) : Wurf {
    override val basispunkte: Int = 10 - vorgaenger.punkte
    override val extrapunkte: Int
        get() = nachfolger()?.basispunkte ?: 0
}

data class Strike(val nachfolger1: () -> Wurf?, val nachfolger2: () -> Wurf?) : Wurf {
    override val basispunkte: Int = 10
    override val extrapunkte: Int
        get() = (nachfolger1()?.basispunkte ?: 0) +
                (nachfolger2()?.basispunkte ?: 0)

}
