package io.github.marmer

import java.time.LocalTime

class GreetingProvider(private val systemTimeProvider: SystemTimeProvider) {
    fun greetingFor(name: String): String {
        val now = systemTimeProvider.currentTime()
        return when {
            now.isMorning() -> "Buenos dias $name"
            now.isEvening() -> "Buenos tarde $name"
            else -> "Buenos noche $name"
        }
    }

    private fun LocalTime.isMorning(): Boolean =
        this isEqualOrAfter morningStart &&
                this isBefore eveningStart

    private fun LocalTime.isEvening(): Boolean =
        this isEqualOrAfter eveningStart &&
                this isBefore nightStart

    private infix fun LocalTime.isEqualOrAfter(time: LocalTime): Boolean = equals(time) || isAfter(time)
    private infix fun LocalTime.isBefore(time: LocalTime): Boolean = isBefore(time)

    private val morningStart = LocalTime.of(6, 0)
    private val eveningStart = LocalTime.of(12, 0)
    private val nightStart = LocalTime.of(20, 0)
}

