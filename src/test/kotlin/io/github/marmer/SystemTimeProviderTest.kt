package io.github.marmer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.Clock
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.test.assertEquals

internal class SystemTimeProviderTest {
    private var underTest: SystemTimeProvider = SystemTimeProvider()

    @Test
    @Throws(Exception::class)
    fun `should return the current time`() {
        // Preparation

        val beforeCall = LocalTime.now()

        // Execution
        val result = underTest.currentTime()

        val afterCall = LocalTime.now()

        // Assertion
        assertAll({ result.isBefore(afterCall) || result.equals(afterCall) },
            { result.isAfter(beforeCall) || result.equals(beforeCall) })
    }

    @Test
    @Throws(Exception::class)
    fun `should serve the current time by the given clock`() {
        // Preparation
        val now = ZonedDateTime.now()
        val underTest = SystemTimeProvider(Clock.fixed(now.toInstant(), ZoneId.systemDefault()))

        // Execution
        val result = underTest.currentTime()

        // Assertion
        assertEquals(now.toLocalTime(), result)
    }
}
