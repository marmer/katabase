package io.github.marmer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class BowlingJavaTest {


    @Test
    fun `alles Ratten`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("--------------------")

        // Assertion
        assertEquals(0, result)
    }

    @Test
    fun `einfache Zahl`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("5--------------------")

        // Assertion
        assertEquals(5, result)
    }

    @Test
    fun `Berechne einfache Zahlenreihe`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("1234----------------")

        // Assertion
        assertEquals(10, result)
    }

    @Test
    fun `Faul zaehlt leer`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("F-------------------")

        // Assertion
        assertEquals(0, result)
    }

    @Test
    fun `Strike allein zaehlt zehn Punkte`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("X------------------")

        // Assertion
        assertEquals(10, result)
    }

    @Test
    fun `Die nachfolgenden Wuerfe eines Strikes werden zum Strike zu addiert`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("X123---------------")

        // Assertion
        assertEquals(19, result)
    }

    @Test
    fun `Keine Wuerfe, keine Punkte`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("")

        // Assertion
        assertEquals(0, result)
    }

    @Test
    fun `Keine Bonuspunkte fuer den Extrawurf`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("XXXXXXXXXXXX")

        // Assertion
        assertEquals(300, result)
    }

    @Test
    fun `Perfektes Spiel ohne Extrawurf`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("XXXXXXXXX14")

        // Assertion
        assertEquals(251, result)
    }

    @Test
    fun `Perfektes Spiel mit einem Extrawurf`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("XXXXXXXXXXX-")

        // Assertion
        assertEquals(290, result)
    }


    @Test
    fun `Spare im Endframe sollte nur vorhergehende Punkte beruecksichtigen`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("--------------------2/3")

        // Assertion
        assertEquals(13, result)
    }

    @Test
    fun `Spare am Ende sollte nur vorgaenger beruecksichtigen`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("------------------X2/")

        // Assertion
        assertEquals(20, result)
    }

    @Test
    fun `Spare sollte vorhergehende und nachfolgende Punkte beruecksichtigen`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("2/3-----------------")

        // Assertion
        assertEquals(16, result)
    }

    @Test
    fun `Akzeptanz mit Extrawurf`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("9/8/X9/8-8/9FXXX9/")

        // Assertion
        assertEquals(191, result)
    }

    @Test
    fun `Akzeptanz ohne Extrawurf`() {
        // Preparation

        // Execution
        val result = Bowling.wurfeToScore("9/8/X9/8-8/9FXX42")

        // Assertion
        assertEquals(158, result)
    }
}
