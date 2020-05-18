package io.github.marmer

typealias Spiel = ArrayList<Frame>

fun wurfeToScore(eingabe: String): Int {
    val spielBuilder = SpielBuilder()
    eingabe.forEach { wurf -> spielBuilder add wurf }

    return spielBuilder.build().map(Frame::punkte).sum()
}

data class Frame(val wuerfe: ArrayList<Wurf> = ArrayList()) {
    val punkte: Int
        get() = wuerfe.sumBy(Wurf::punkte)
}

class SpielBuilder {
    private var wurfContext = ArrayList<Wurf>()
    private var spiel = newSpiel()

    infix fun add(input: Char) {
        val wurf = input.toWurf()

        wurfContext.add(wurf)

        spiel.add(wurf)
    }

    private fun Char.toWurf(): Wurf {
        val inEndgame = spiel.isEndspiel()
        val currentWurfIndex = wurfContext.lastIndex + 1

        return when {
            isStrike() -> Strike(
                { if (inEndgame) null else wurfContext.get(currentWurfIndex + 1) },
                { if (inEndgame) null else wurfContext.get(currentWurfIndex + 2) })

            isSpare() -> Spare(wurfContext.last()) {
                if (inEndgame) null else wurfContext.get(currentWurfIndex + 1)
            }

            isPoints() -> Points(Character.getNumericValue(this))

            else -> Points(0)
        }
    }

    private fun Char.isPoints() = isDigit()

    private fun Char.isSpare() = this == '/'

    private fun Char.isStrike() = this == 'X'

    fun build(): Spiel {
        val retVal = spiel
        spiel = newSpiel()
        return retVal
    }
}


//Spiel extension functions
private fun Spiel.add(wurf: Wurf) {
    val wuerfe = ArrayList(currentFrameWuerfe())
    wuerfe.add(wurf)
    this[lastIndex] = Frame(wuerfe)

    if (isReadyForNewFrame()) {
        add(Frame())
    }
}

private fun Spiel.isReadyForNewFrame(): Boolean = !isEndspiel() &&
        (currentFrameWuerfe().size >= 2 || currentFrameWuerfe().last() is Strike)

private fun Spiel.currentFrameWuerfe() = last().wuerfe

private fun Spiel.isEndspiel() = size >= 10

private fun newSpiel() = arrayListOf(Frame())


//Würfe
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
