package io.github.marmer

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BowlingTest {


    @Test
    @DisplayName("alles Ratten")
    fun berechne_allesRatten() {
        // Preparation

        // Execution
        val result = wurfeToScore("--------------------")

        // Assertion
        assertEquals(0, result)
    }

    @Test
    @DisplayName("einfache Zahl")
    fun berechne_EinfacheZahl() {
        // Preparation

        // Execution
        val result = wurfeToScore("5--------------------")

        // Assertion
        assertEquals(5, result)
    }

    @Test
    @DisplayName("BerechneEinfacheZahlenreihe")
    fun berechne_BerechneEinfacheZahlenreihe() {
        // Preparation

        // Execution
        val result = wurfeToScore("1234----------------")

        // Assertion
        assertEquals(10, result)
    }

    @Test
    @DisplayName("Faul zählt leer")
    fun berechne_FaulZaehltLeer() {
        // Preparation

        // Execution
        val result = wurfeToScore("F-------------------")

        // Assertion
        assertEquals(0, result)
    }

    @Test
    @DisplayName("Strike allein zählt zehn Punkte")
    fun berechne_StrikeAlleinZaehltZehnPunkte() {
        // Preparation

        // Execution
        val result = wurfeToScore("X------------------")

        // Assertion
        assertEquals(10, result)
    }

    @Test
    @DisplayName("Die nachfolgenden Würfe eines Strikes werden zum Strike zu addiert")
    fun berechne_DieNachfolgendenWuerfeEinesStrikesWerdenZumStrikeZuAddiert() {
        // Preparation

        // Execution
        val result = wurfeToScore("X123---------------")

        // Assertion
        assertEquals(19, result)
    }

    @Test
    @DisplayName("Keine Würfe, keine Punkte")
    fun berechne_KeineWuerfeKeinePunkte() {
        // Preparation

        // Execution
        val result = wurfeToScore("")

        // Assertion
        assertEquals(0, result)
    }

    @Test
    @DisplayName("Keine Bonuspunkte für den Extrawurf")
    fun berechne_PerfektesSpiel() {
        // Preparation

        // Execution
        val result = wurfeToScore("XXXXXXXXXXXX")

        // Assertion
        assertEquals(300, result)
    }

    @Test
    @DisplayName("Perfektes Spiel ohne Extrawurf")
    fun berechne_PerfektesSpielOhneExtrawurf() {
        // Preparation

        // Execution
        val result = wurfeToScore("XXXXXXXXX14")

        // Assertion
        assertEquals(251, result)
    }

    @Test
    @DisplayName("Perfektes Spiel mit einem Extrawurf")
    fun berechne_PerfektesSpielMitEinemExtrawurf() {
        // Preparation

        // Execution
        val result = wurfeToScore("XXXXXXXXXXX-")

        // Assertion
        assertEquals(290, result)
    }


    @Test
    @DisplayName("Spare im Endframe sollte nur vorhergehende Punkte berücksichtigen")
    fun testMethodName_SpareImEndframeSollteVorhergehendeVorhergehendePunkteBeruecksichtigen() {
        // Preparation

        // Execution
        val result = wurfeToScore("--------------------2/3")

        // Assertion
        assertEquals(13, result)
    }

    @Test
    @DisplayName("Spare am Ende sollte nur vorgänger beruecksichtigen")
    fun berechne_SpareAmEndeSollteNurVorgaengerBeruecksichtigen() {
        // Preparation

        // Execution
        val result = wurfeToScore("------------------X2/")

        // Assertion
        assertEquals(20, result)
    }

    @Test
    @DisplayName("Spare sollte vorhergehende und nachfolgende Punkte berücksichtigen")
    fun berechne_SpareSollteVorhergehendeUndNachfolgendePunkteBeruecksichtigen() {
        // Preparation

        // Execution
        val result = wurfeToScore("2/3-----------------")

        // Assertion
        assertEquals(16, result)
    }

    @Test
    @DisplayName("Akzeptanz mit Extrawurf")
    fun berechne_AkzeptanzMitExtrawurf() {
        // Preparation

        // Execution
        val result = wurfeToScore("9/8/X9/8-8/9FXXX9/")

        // Assertion
        assertEquals(191, result)
    }

    @Test
    @DisplayName("Akzeptanz ohne Extrawurf")
    fun berechne_AkzeptanzOhneExtrawurf() {
        // Preparation

        // Execution
        val result = wurfeToScore("9/8/X9/8-8/9FXX42")

        // Assertion
        assertEquals(158, result)
    }
}
