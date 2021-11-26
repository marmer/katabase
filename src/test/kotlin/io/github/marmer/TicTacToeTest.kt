package io.github.marmer

import io.github.marmer.TicTacToe.Player
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TicTacToeTest {

    @Test
    fun `Soll ganzes Board darstellen`() {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(Player.O, 2, 2) //Middle

        // Assertion
        Assertions.assertArrayEquals(
            arrayOf<Array<Player?>>(
                arrayOf(null, null, null),
                arrayOf(null, Player.O, null),
                arrayOf(null, null, null)
            ), underTest.getBoard()
        )
    }

    @Test
    fun `Es sollte moeglich sein mehrere unterschiedliche Felder nacheinander zu besetzen`() {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(Player.O, 2, 2)
        underTest.set(Player.X, 1, 1)

        // Assertion
        Assertions.assertArrayEquals(
            arrayOf<Array<Player?>>(
                arrayOf(Player.X, null, null),
                arrayOf(null, Player.O, null),
                arrayOf(null, null, null)
            ), underTest.getBoard()
        )
    }

    @Test
    fun `Das Board sollte immutable sein`() {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(Player.O, 2, 2)
        underTest.set(Player.X, 1, 1)

        underTest.getBoard()[0][0] = null;

        // Assertion
        Assertions.assertArrayEquals(
            arrayOf<Array<Player?>>(
                arrayOf(Player.X, null, null),
                arrayOf(null, Player.O, null),
                arrayOf(null, null, null)
            ), underTest.getBoard()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "0,1",
        "4,1",
        "1,0",
        "4,0"
    )
    fun `Beim Versuch ein Feld ausserhalb des Spielfelts zu setzen fliegt eine vernuenftige Exception`(
        x: Int,
        y: Int
    ) {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        val thrown =
            assertThrows<TicTacToe.CoordinateOutOfFieldException> { underTest.set(Player.X, x, y) }

        // Assertion
        assertEquals("Coordinate $x,$y is out of range", thrown.message)
    }

    @ParameterizedTest
    @CsvSource(
        "1,1,X",
        "2,2,X",
        "1,1,O",
        "2,2,O"
    )
    fun `Bei schon besetzten Feldern sollten eine vernuenftige Exception fliegen`(
        x: Int,
        y: Int,
        player: Player
    ) {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(Player.O, 1, 1)
        underTest.set(Player.X, 2, 2)

        // Execution
        val thrown =
            assertThrows<TicTacToe.FieldAlreadyInUseException> { underTest.set(Player.O, x, y) }

        // Assertion
        assertEquals("Field $x,$y is already in use", thrown.message)
    }

    @ParameterizedTest
    @CsvSource(
        "1,1, 2,1, 3,1, 1,3, 2,3, 2,2", //Horizontal1 - EmptyFieldTry
        "1,2, 2,2, 3,2, 1,3, 2,3, 1,1", //Horizontal2 - EmptyFieldTry
        "1,3, 2,3, 3,3, 1,2, 2,2, 1,1", //Horizontal3 - EmptyFieldTry

        "1,1, 1,2, 1,3, 3,1, 3,2, 2,2", //Vertikal1 - EmptyFieldTry
        "2,1, 2,2, 2,3, 3,1, 3,2, 1,1", //Vertikal2 - EmptyFieldTry
        "3,1, 3,2, 3,3, 2,1, 2,2, 1,1", //Vertikal3 - EmptyFieldTry

        "1,1, 2,2, 3,3, 1,3, 3,1, 1,2", //Diagonal1 - EmptyFieldTry
        "1,3, 2,2, 3,1, 1,1, 3,3, 1,2", //Diagonal2 - EmptyFieldTry

        "1,1, 2,2, 3,3, 1,3, 3,1, 2,2", //Diagonal1 - NonEmptyFieldTry
    )
    fun `Bei gewonnenem Spiel sollte kein Zug mehr moeglich sein und is win true lifern`(
        xX1: Int, yX1: Int, //X 1. Move
        xX2: Int, yX2: Int, //X 2. Move
        xX3: Int, yX3: Int, //X 3. Move
        xO1: Int, yO1: Int, //O 1. Move
        xO2: Int, yO2: Int, //O 2. Move
        xO3: Int, yO3: Int, //O Not Allowed Move
    ) {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(Player.X, xX1, yX1)
        underTest.set(Player.O, xO1, yO1)
        underTest.set(Player.X, xX2, yX2)
        underTest.set(Player.O, xO2, yO2)
        underTest.set(Player.X, xX3, yX3)

        // Execution
        val thrown =
            assertThrows<TicTacToe.GameFinishedException> { underTest.set(Player.O, xO2, yO2) }

        // Assertion
        assertEquals("Game is aleady finished", thrown.message)
    }

    @ParameterizedTest
    @CsvSource(
        "1,1, 2,1, 3,1, 1,3, 2,3", //Horizontal1
        "1,2, 2,2, 3,2, 1,3, 2,3", //Horizontal2
        "1,3, 2,3, 3,3, 1,2, 2,2", //Horizontal3

        "1,1, 1,2, 1,3, 3,1, 3,2", //Vertikal1
        "2,1, 2,2, 2,3, 3,1, 3,2", //Vertikal2
        "3,1, 3,2, 3,3, 2,1, 2,2", //Vertikal3

        "1,1, 2,2, 3,3, 1,3, 3,1", //Diagonal1
        "1,3, 2,2, 3,1, 1,1, 3,3", //Diagonal2
    )
    fun `isWon sollte bei gewonnenen Kombinationen true liefern`(
        xX1: Int, yX1: Int, //X 1. Move
        xX2: Int, yX2: Int, //X 2. Move
        xX3: Int, yX3: Int, //X 3. Move
        xO1: Int, yO1: Int, //O 1. Move
        xO2: Int, yO2: Int, //O 2. Move
    ) {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(Player.X, xX1, yX1)
        underTest.set(Player.O, xO1, yO1)
        underTest.set(Player.X, xX2, yX2)
        underTest.set(Player.O, xO2, yO2)
        underTest.set(Player.X, xX3, yX3)

        // Execution
        val thrown =
            assertThrows<TicTacToe.GameFinishedException> { underTest.set(Player.O, xO2, yO2) }

        // Assertion
        assertTrue(underTest.isGameWon(), "Gewonnen")
    }

    @ParameterizedTest
    @CsvSource(
        "1,1, 2,1, 1,3, 2,3", //Horizontal1
        "1,2, 2,2, 1,3, 2,3", //Horizontal2
        "1,3, 2,3, 1,2, 2,2", //Horizontal3

        "1,1, 1,2, 3,1, 3,2", //Vertikal1
        "2,1, 2,2, 3,1, 3,2", //Vertikal2
        "3,1, 3,2, 2,1, 2,2", //Vertikal3

        "1,1, 2,2, 1,3, 3,1", //Diagonal1
        "1,3, 2,2, 1,1, 3,3", //Diagonal2
    )
    fun `isWon sollte bei nicht gewonnenen Kombinationen false liefern`(
        xX1: Int, yX1: Int, //X 1. Move
        xX2: Int, yX2: Int, //X 2. Move
        xO1: Int, yO1: Int, //O 1. Move
        xO2: Int, yO2: Int, //O 2. Move
    ) {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(Player.X, xX1, yX1)
        underTest.set(Player.O, xO1, yO1)
        underTest.set(Player.X, xX2, yX2)
        underTest.set(Player.O, xO2, yO2)

        // Execution

        // Assertion
        assertFalse(underTest.isGameWon(), "Gewonnen")
    }

    @ParameterizedTest
    @CsvSource("O", "X")
    fun `isWon sollte bei nicht gewonnenen Kombinationen false liefern`(player: Player) {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(player, 1, 1)

        // Execution
        val thrown = assertThrows<TicTacToe.DoubleMoveException> { underTest.set(player, 1, 2) }

        // Assertion
        assertEquals("You can only set one field at a time player $player", thrown.message)
    }

    @Test
    fun `isWon sollte bei leeremFeld false liefern`() {
        // Preparation
        val underTest = TicTacToe()

        // Execution

        // Assertion
        assertFalse(underTest.isGameWon(), "Gewonnen")
    }


    @Test
    fun `ToString liefert lesbare Darstellung des Feldes`() {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(Player.X, 1, 1)
        underTest.set(Player.O, 2, 2)
        underTest.set(Player.X, 3, 3)

        // Assertion
        assertEquals(
            """
            X..
            .O.
            ..X
        """.trimIndent(),
            underTest.toString()
        )
    }
}
