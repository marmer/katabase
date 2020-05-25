package io.github.marmer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RingbufferTest {

    @Test
    @Throws(Exception::class)
    fun `take - Ringbuffer sollte nichts liefern, wenn er leer ist`() {
        // Preparation
        val underTest = Ringbuffer<String>(5)

        // Execution
        val result = underTest.take()

        // Assertion
        assertThat(result).isNull()
    }

    @Test
    @Throws(Exception::class)
    fun `take - Ringbuffer sollte Werte in der Reihenfolge liefern, in der sie eingetragen wurden`() {
        // Preparation
        val underTest = Ringbuffer<String>(5)
        underTest.add("A", "B")

        // Execution
        // Assertion
        assertThat(underTest.take()).isEqualTo("A")
        assertThat(underTest.take()).isEqualTo("B")
    }

    @Test
    @Throws(Exception::class)
    fun `take - Nach dem Lesen aller Werte sollten keine Ergebnisse mehr geliefert werden `() {
        // Preparation
        val underTest = Ringbuffer<String>(2)
        underTest.add("A", "B")

        // Execution
        // Assertion
        assertThat(underTest.take()).isEqualTo("A")
        assertThat(underTest.take()).isEqualTo("B")
        assertThat(underTest.take()).isEqualTo(null)
    }

    @Test
    @Throws(Exception::class)
    fun `take - der aelteste nicht gelesene Wert sollte ueberschrieben werden`() {
        // Preparation
        val underTest = Ringbuffer<String>(2)
        underTest.add("A", "B", "C")

        // Execution
        // Assertion
        assertThat(underTest.take()).isEqualTo("B")
        assertThat(underTest.take()).isEqualTo("C")
    }

    @Test
    @Throws(Exception::class)
    fun `count - count sollte bei einem leeren buffer leer sein`() {
        // Preparation
        val underTest = Ringbuffer<String>(2)

        // Execution
        // Assertion
        assertThat(underTest.count).isEqualTo(0)
    }

    @Test
    @Throws(Exception::class)
    fun `count - count sollte eine groesse entsprechend der Fuellung des Buffers liefern`() {
        // Preparation
        val underTest = Ringbuffer<String>(3)
        underTest.add("A", "B")

        // Execution
        // Assertion
        assertThat(underTest.count).isEqualTo(2)
    }

    @Test
    @Throws(Exception::class)
    fun `count - sollte ueberlauf bei der Zaehlung beruecksichtigen`() {
        // Preparation
        val underTest = Ringbuffer<String>(3)
        underTest.add("A", "B", "C", "D", "E")

        // Execution
        // Assertion
        assertThat(underTest.count).isEqualTo(3)
    }


    @Test
    @Throws(Exception::class)
    fun `count - sollte gelesene Werte beruecksichtigen`() {
        // Preparation
        val underTest = Ringbuffer<String>(3)
        underTest.add("A", "B", "C")
        underTest.take()

        // Execution
        // Assertion
        assertThat(underTest.count).isEqualTo(2)
    }
}
