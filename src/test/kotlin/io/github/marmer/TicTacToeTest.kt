package io.github.marmer

import io.github.marmer.TicTacToe.Player
import io.github.marmer.TicTacToe.Player.O
import io.github.marmer.TicTacToe.Player.X
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TicTacToeTest {

    @Test
    fun `Soll ganzes Board darstellen`() {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(O, 2, 2) //Middle

        // Assertion
        assertArrayEquals(
            arrayOf<Array<Player?>>(
                arrayOf(null, null, null),
                arrayOf(null, O, null),
                arrayOf(null, null, null)
            ), underTest.getBoard()
        )
    }

    @Test
    fun `Es sollte moeglich sein mehrere unterschiedliche Felder nacheinander zu besetzen`() {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(O, 2, 2)
        underTest.set(X, 1, 1)

        // Assertion
        assertArrayEquals(
            arrayOf<Array<Player?>>(
                arrayOf(X, null, null),
                arrayOf(null, O, null),
                arrayOf(null, null, null)
            ), underTest.getBoard()
        )
    }

    @Test
    fun `Das Board sollte immutable sein`() {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        underTest.set(O, 2, 2)
        underTest.set(X, 1, 1)

        underTest.getBoard()[0][0] = null;

        // Assertion
        assertArrayEquals(
            arrayOf<Array<Player?>>(
                arrayOf(X, null, null),
                arrayOf(null, O, null),
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
            assertThrows<TicTacToe.CoordinateOutOfFieldException> { underTest.set(X, x, y) }

        // Assertion
        assertEquals("Coordinate $x,$y is out of range", thrown.message)
    }

    @ParameterizedTest
    @CsvSource(
        "0,1",
        "4,1",
        "1,0",
        "4,0"
    )
    fun `Beim Versuch ein Feld ausserhalb des Spielfelts abzufragen fliegt eine vernuenftige Exception`(
        x: Int,
        y: Int
    ) {
        // Preparation
        val underTest = TicTacToe()

        // Execution
        val thrown =
            assertThrows<TicTacToe.CoordinateOutOfFieldException> { underTest.getField(x, y) }

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
        underTest.set(O, 1, 1)
        underTest.set(X, 2, 2)

        // Execution
        val thrown =
            assertThrows<TicTacToe.FieldAlreadyInUseException> { underTest.set(O, x, y) }

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
        underTest.set(X, xX1, yX1)
        underTest.set(O, xO1, yO1)
        underTest.set(X, xX2, yX2)
        underTest.set(O, xO2, yO2)
        underTest.set(X, xX3, yX3)

        // Execution
        val thrown =
            assertThrows<TicTacToe.GameFinishedException> { underTest.set(O, xO2, yO2) }

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
        underTest.set(X, xX1, yX1)
        underTest.set(O, xO1, yO1)
        underTest.set(X, xX2, yX2)
        underTest.set(O, xO2, yO2)
        underTest.set(X, xX3, yX3)

        // Execution
        val thrown =
            assertThrows<TicTacToe.GameFinishedException> { underTest.set(O, xO2, yO2) }

        // Assertion
        assertTrue(underTest.isGameWon(), "Won")
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
        underTest.set(X, xX1, yX1)
        underTest.set(O, xO1, yO1)
        underTest.set(X, xX2, yX2)
        underTest.set(O, xO2, yO2)

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
        underTest.set(X, 1, 1)
        underTest.set(O, 2, 2)
        underTest.set(X, 3, 3)

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

    //####### Variable Size Tests ###########

    @Test
    fun `ToString mit anderer Groesse liefert lesbare Darstellung des Feldes`() {
        // Preparation
        val underTest = TicTacToe(5)

        // Execution
        underTest.set(X, 1, 1)
        underTest.set(O, 2, 2)
        underTest.set(X, 5, 5)

        // Assertion
        assertEquals(
            """
            X....
            .O...
            .....
            .....
            ....X
        """.trimIndent(),
            underTest.toString()
        )
    }

    @Test
    fun `Kleinere Felder als in groesse 3 sollten nicht moeglich sein`() {
        // Preparation

        // Execution
        val thrown = assertThrows<TicTacToe.InvalidFieldSizeException> { TicTacToe(2) }

        // Assertion
        assertEquals("The field size must at least be 3", thrown.message)
    }

    companion object {
        @JvmStatic
        fun winConditionVarSize() = arrayOf(
            // Win Setups
            arrayOf(
                TicTacToe(4),
                listOf( //Diagonal 1
                    X to (1 to 1), O to (2 to 1),
                    X to (2 to 2), O to (3 to 1),
                    X to (3 to 3), O to (4 to 1),
                    X to (4 to 4)
                ), true
            ),
            arrayOf(
                TicTacToe(4),
                listOf( //Diagonal 2
                    X to (1 to 4), O to (4 to 2),
                    X to (2 to 3), O to (4 to 3),
                    X to (3 to 2), O to (4 to 4),
                    X to (4 to 1)
                ), true
            ),
            arrayOf(
                TicTacToe(5),
                listOf( //Diagonal 1
                    X to (1 to 1), O to (2 to 1),
                    X to (2 to 2), O to (3 to 1),
                    X to (3 to 3), O to (4 to 1),
                    X to (4 to 4), O to (5 to 1),
                    X to (5 to 5)
                ), true
            ),
            arrayOf(
                TicTacToe(5),
                listOf( //Diagonal 2
                    X to (1 to 5), O to (5 to 2),
                    X to (2 to 4), O to (5 to 3),
                    X to (3 to 3), O to (5 to 4),
                    X to (4 to 2), O to (5 to 5),
                    X to (5 to 1)
                ), true
            ),
            arrayOf(
                TicTacToe(4),
                listOf(
                    // Horizontal
                    X to (1 to 2), O to (1 to 3),
                    X to (2 to 2), O to (2 to 3),
                    X to (3 to 2), O to (3 to 3),
                    X to (4 to 2),
                ), true
            ),
            arrayOf(
                TicTacToe(5),
                listOf(
                    // Horizontal
                    X to (1 to 2), O to (1 to 3),
                    X to (2 to 2), O to (2 to 3),
                    X to (3 to 2), O to (3 to 3),
                    X to (4 to 2), O to (4 to 3),
                    X to (5 to 2),
                ), true
            ),
            arrayOf(
                TicTacToe(4),
                listOf(
                    // Vertical
                    X to (2 to 1), O to (3 to 1),
                    X to (2 to 2), O to (3 to 2),
                    X to (2 to 3), O to (3 to 3),
                    X to (2 to 4),
                ), true
            ),
            arrayOf(
                TicTacToe(5),
                listOf(
                    // Vertical
                    X to (2 to 1), O to (3 to 1),
                    X to (2 to 2), O to (3 to 2),
                    X to (2 to 3), O to (3 to 3),
                    X to (2 to 4), O to (3 to 4),
                    X to (2 to 5),
                ), true
            ),

            //NonWinSetups
            arrayOf(
                TicTacToe(4),
                listOf(
                    //Diagonal 1
                    X to (1 to 1), O to (2 to 1),
                    X to (2 to 2), O to (3 to 1),
                    X to (3 to 3), O to (4 to 1),
                ), false
            ),
            arrayOf(
                TicTacToe(4),
                listOf(
                    //Diagonal 2
                    X to (1 to 4), O to (4 to 2),
                    X to (2 to 3), O to (4 to 3),
                    X to (3 to 2), O to (4 to 4),
                ), false
            ),
            arrayOf(
                TicTacToe(5),
                listOf(
                    //Diagonal 1
                    X to (1 to 1), O to (2 to 1),
                    X to (2 to 2), O to (3 to 1),
                    X to (3 to 3), O to (4 to 1),
                    X to (4 to 4), O to (5 to 1),
                ), false
            ),
            arrayOf(
                TicTacToe(5),
                listOf(
                    //Diagonal 2
                    X to (1 to 5), O to (5 to 2),
                    X to (2 to 4), O to (5 to 3),
                    X to (3 to 3), O to (5 to 4),
                    X to (4 to 2), O to (5 to 5),
                ), false
            ),
            arrayOf(
                TicTacToe(4),
                listOf(
                    // Horizontal
                    X to (1 to 2), O to (1 to 3),
                    X to (2 to 2), O to (2 to 3),
                    X to (3 to 2), O to (3 to 3),
                ), false
            ),
            arrayOf(
                TicTacToe(5),
                listOf(
                    // Horizontal
                    X to (1 to 2), O to (1 to 3),
                    X to (2 to 2), O to (2 to 3),
                    X to (3 to 2), O to (3 to 3),
                    X to (4 to 2), O to (4 to 3),
                ), false
            ),
            arrayOf(
                TicTacToe(4),
                listOf(
                    // Vertical
                    X to (2 to 1), O to (3 to 1),
                    X to (2 to 2), O to (3 to 2),
                    X to (2 to 3), O to (3 to 3),
                ), false
            ),
            arrayOf(
                TicTacToe(5),
                listOf(
                    // Vertical
                    X to (2 to 1), O to (3 to 1),
                    X to (2 to 2), O to (3 to 2),
                    X to (2 to 3), O to (3 to 3),
                    X to (2 to 4), O to (3 to 4),
                ), false
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("winConditionVarSize")
    fun `Winning Conditions with different Sizes should work`(
        underTest: TicTacToe,
        steps: List<Pair<Player, Pair<Int, Int>>>,
        expectedWinResult: Boolean
    ) {
        // Preparation
        steps.forEach { (player, coordinate) ->
            val (x, y) = coordinate
            underTest.set(player, x, y)
        }


        // Execution
        val result = underTest.isGameWon()

        // Assertion
        assertEquals(expectedWinResult, result, "Win Result")
    }

}
