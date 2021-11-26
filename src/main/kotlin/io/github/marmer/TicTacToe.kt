package io.github.marmer

import kotlinx.coroutines.*

class TicTacToe(
    private val boardSize: Int = 3,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    init {
        if (boardSize < 3) throw InvalidFieldSizeException(3)
    }

    private var playerOfLastMove: Player? = null

    private val board: Array<Array<Player?>> =
        (1..boardSize).map {
            (1..boardSize).map { null }.toTypedArray<Player?>()
        }.toTypedArray()

    fun set(player: Player, x: Int, y: Int) = when {
        isDoublwMoveTryFor(player) -> throw DoubleMoveException(player)
        isGameWon() -> throw GameFinishedException()
        isCoordinateInField(x, y) -> throw CoordinateOutOfFieldException(x, y)
        isFieldAlreadyInUse(x, y) -> throw FieldAlreadyInUseException(x, y)
        else -> {
            board[x - 1][y - 1] = player
            playerOfLastMove = player
        }
    }

    private fun isFieldAlreadyInUse(x: Int, y: Int) = board[x - 1][y - 1] != null

    private fun isCoordinateInField(x: Int, y: Int) =
        !(x in 1..boardSize) || !(y in 1..boardSize)

    private fun isDoublwMoveTryFor(player: Player) = playerOfLastMove == player


    /**
     * WARNING!!! This method is not threadsafe because the data accessed within this method are mutable!
     */
    fun isGameWon(): Boolean = runBlocking {
        val rows =
            async(coroutineDispatcher) {
                (1..boardSize).map { y ->
                    (1..boardSize).map { x ->
                        async(coroutineDispatcher) {
                            getField(x, y)
                        }
                    }.awaitAll()
                }
            }


        val columns =
            async(coroutineDispatcher) {
                (1..boardSize).map { x ->
                    (1..boardSize).map { y ->
                        async(coroutineDispatcher) {
                            getField(x, y)
                        }
                    }.awaitAll()
                }
            }


        val diagonal1 =
            async(coroutineDispatcher) {
                listOf((1..boardSize).map {
                    getField(it, it)
                })
            }

        val diagonal2 =
            async(coroutineDispatcher) {
                listOf((1..boardSize).map {
                    getField(it, boardSize - (it - 1))
                })
            }

        awaitAll(rows, columns, diagonal1, diagonal2).flatten()
            .any { isWinningLine(it) }
    }

    private fun isWinningLine(lineOfFields: List<Player?>): Boolean {
        val numberOfFieldsByPlayer = lineOfFields.groupBy { it }
        return numberOfFieldsByPlayer.get(Player.X)?.size == boardSize ||
                numberOfFieldsByPlayer.get(Player.O)?.size == boardSize
    }

    fun getField(x: Int, y: Int): Player? {
        Thread.sleep(1)
        return if (isCoordinateInField(x, y)) throw CoordinateOutOfFieldException(x, y)
        else board[x - 1][y - 1]
    }

    fun getBoard(): Array<Array<Player?>> =
        board.map { it.copyOf() }
            .toTypedArray()

    enum class Player {
        X, O
    }

    class CoordinateOutOfFieldException(x: Int, y: Int) :
        RuntimeException("Coordinate $x,$y is out of range")

    class FieldAlreadyInUseException(x: Int, y: Int) :
        RuntimeException("Field $x,$y is already in use")

    class GameFinishedException :
        RuntimeException("Game is aleady finished")

    class DoubleMoveException(player: Player) :
        RuntimeException("You can only set one field at a time player $player")

    class InvalidFieldSizeException(minSize: Int) :
        RuntimeException("The field size must at least be $minSize")


    override fun toString(): String {
        return (1..boardSize).map { x ->
            (1..boardSize).map { y -> fieldString(x, y) }.joinToString("")
        }.joinToString("\n")
    }

    private fun fieldString(x: Int, y: Int): String {
        return getField(x, y)?.toString() ?: "."
    }
}

