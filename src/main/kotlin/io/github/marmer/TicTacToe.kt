package io.github.marmer

class TicTacToe {
    private var playerOfLastMove: Player? = null

    private val board: Array<Array<Player?>> = arrayOf(
        arrayOf(null, null, null),
        arrayOf(null, null, null),
        arrayOf(null, null, null)
    )

    fun set(player: Player, x: Int, y: Int) = when {
        isDoublwMoveTryFor(player) -> throw DoubleMoveException(player)
        isGameWon() -> throw GameFinishedException()
        isValidCoordinate(x, y) -> throw CoordinateOutOfFieldException(x, y)
        isFieldAlreadyInUse(x, y) -> throw FieldAlreadyInUseException(x, y)
        else -> {
            board[x - 1][y - 1] = player
            playerOfLastMove = player
        }
    }

    private fun isFieldAlreadyInUse(x: Int, y: Int) = board[x - 1][y - 1] != null

    private fun isValidCoordinate(x: Int, y: Int) = !(x in 1..3 && y in 1..3)

    private fun isDoublwMoveTryFor(player: Player) = playerOfLastMove == player

    fun isGameWon(): Boolean =
        isWinnerRow(listOf(getField(1, 1), getField(2, 1), getField(3, 1))) || //H1
                isWinnerRow(listOf(getField(1, 2), getField(2, 2), getField(3, 2))) || //H2
                isWinnerRow(listOf(getField(1, 3), getField(2, 3), getField(3, 3))) || //H3
                isWinnerRow(listOf(getField(1, 1), getField(1, 2), getField(1, 3))) || //V1
                isWinnerRow(listOf(getField(2, 1), getField(2, 2), getField(2, 3))) || //V2
                isWinnerRow(listOf(getField(3, 1), getField(3, 2), getField(3, 3))) || //V3
                isWinnerRow(listOf(getField(1, 1), getField(2, 2), getField(3, 3))) || //D1
                isWinnerRow(listOf(getField(1, 3), getField(2, 2), getField(3, 1)))  //D2

    private fun isWinnerRow(fieldsInRow: List<Player?>) =
        fieldsInRow.count {
            it == Player.O
        } == 3 || fieldsInRow.count { it == Player.X } == 3

    fun getField(x: Int, y: Int): Player? = board[x - 1][y - 1]

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


    override fun toString(): String {
        return """
            ${fieldString(1, 1)}${fieldString(2, 1)}${fieldString(3, 1)}
            ${fieldString(1, 2)}${fieldString(2, 2)}${fieldString(3, 2)}
            ${fieldString(1, 3)}${fieldString(2, 3)}${fieldString(3, 3)}
        """.trimIndent()
    }

    private fun fieldString(x: Int, y: Int): String {
        return getField(x, y)?.toString() ?: "."
    }
}

