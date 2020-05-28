package io.github.marmer

typealias CellGrid = Array<Array<Boolean>>

fun evolve(grid: String): String =
    grid.toCellGrid()
        .evolve()
        .toCellString()

internal fun CellGrid.evolve(): CellGrid {
    return mapIndexed { y, line ->
        line.mapIndexed { x, isCellIsAlive ->
            isAliveAfterAvolution(isCellIsAlive, getLivingNeighbourCountFor(y, x))
        }.toTypedArray()
    }.toTypedArray()
}

internal fun isAliveAfterAvolution(cellIsAlive: Boolean, neighbourCount: Int) =
    if (cellIsAlive)
        neighbourCount in 2..3
    else
        neighbourCount == 3

internal fun CellGrid.getLivingNeighbourCountFor(y: Int, x: Int): Int {
    val isAtTopBoarder = y <= 0
    val isAtLeftBoarder = x <= 0
    val isAtBottomBoarder = y >= this.lastIndex
    val isAtRightBoarder = x >= this[y].lastIndex

    val hasLivingTop = !isAtTopBoarder && this[y - 1][x]
    val hasLivingBottom = !isAtBottomBoarder && this[y + 1][x]
    val hasLivingLeft = !isAtLeftBoarder && this[y][x - 1]
    val hasLivingRight = !isAtRightBoarder && this[y][x + 1]
    val hasLivingTopLeft = !isAtTopBoarder && !isAtLeftBoarder && this[y - 1][x - 1]
    val hasLivingTopRight = !isAtTopBoarder && !isAtRightBoarder && this[y - 1][x + 1]
    val hasLivingBottomLeft = !isAtBottomBoarder && !isAtLeftBoarder && this[y + 1][x - 1]
    val hasLivingBottomRight = !isAtBottomBoarder && !isAtRightBoarder && this[y + 1][x + 1]

    return (if (hasLivingTop) 1 else 0) +
            (if (hasLivingBottom) 1 else 0) +
            (if (hasLivingLeft) 1 else 0) +
            (if (hasLivingRight) 1 else 0) +
            (if (hasLivingTopLeft) 1 else 0) +
            (if (hasLivingTopRight) 1 else 0) +
            (if (hasLivingBottomLeft) 1 else 0) +
            if (hasLivingBottomRight) 1 else 0
}

internal fun CellGrid.toCellString(): String = map { line ->
    line.map { if (it) "*" else "." }
        .joinToString(separator = "")
}.joinToString(separator = "\n")

internal fun String.toCellGrid(): CellGrid = lines()
    .map { line ->
        line.map { c -> c.equals('*') }
            .toTypedArray()
    }
    .toTypedArray()
