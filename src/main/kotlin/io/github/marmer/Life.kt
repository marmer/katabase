package io.github.marmer

typealias CellGrid = Array<Array<Cell>>

data class Life(private val universe: String) {
    private val cellGrid: CellGrid = universe.toCellGrid()
        .apply { withKnownNeighbours() }


    fun evolve(): Life = Life(cellGrid.let(CellGrid::evolve).toCellString())

    override fun toString(): String = cellGrid.toCellString()
}

private fun CellGrid.evolve(): CellGrid =
    map { arrayOfCells -> arrayOfCells.map { cell -> cell.evolve() }.toTypedArray() }.toTypedArray()

private fun String.toCellGrid(): CellGrid = lines()
    .map { line ->
        line.map { c -> Cell(c == '*') }
            .toTypedArray()
    }.toTypedArray()

private fun CellGrid.toCellString(): String = map { line ->
    line.map { cell ->
        if (cell.alive) '*'
        else '.'
    }.joinToString("")
}.joinToString("\n")

private fun CellGrid.withKnownNeighbours() =
    (0..this.lastIndex).forEach { y ->
        (0..this[y].lastIndex).forEach { x ->
            val atBottomBorder = y == this.lastIndex
            val atTopBorder = y == 0
            val atLeftBorder = x == 0
            val atRightBorder = x == this[y].lastIndex

            val currentCell = this[y][x]

            if (!atRightBorder) this[y][x + 1] = this[y][x + 1] withNeighbour currentCell
            if (!atLeftBorder) this[y][x - 1] = this[y][x - 1] withNeighbour currentCell
            if (!atBottomBorder) this[y + 1][x] = this[y + 1][x] withNeighbour currentCell
            if (!atTopBorder) this[y - 1][x] = this[y - 1][x] withNeighbour currentCell

            if (!atBottomBorder && !atRightBorder) this[y + 1][x + 1] = this[y + 1][x + 1] withNeighbour currentCell
            if (!atBottomBorder && !atLeftBorder) this[y + 1][x - 1] = this[y + 1][x - 1] withNeighbour currentCell
            if (!atTopBorder && !atRightBorder) this[y - 1][x + 1] = this[y - 1][x + 1] withNeighbour currentCell
            if (!atTopBorder && !atLeftBorder) this[y - 1][x - 1] = this[y - 1][x - 1] withNeighbour currentCell
        }
    }


data class Cell(val alive: Boolean, val neighbours: List<Cell> = emptyList()) {
    infix fun withNeighbour(neighbour: Cell): Cell = copy(neighbours = neighbours.plus(neighbour))
    fun evolve() =
        copy(
            alive = alive && livingNeighbours() in 2..3 ||
                    !alive && livingNeighbours() == 3,
            neighbours = emptyList()
        )

    private fun livingNeighbours(): Int = neighbours.count(Cell::alive)
}
