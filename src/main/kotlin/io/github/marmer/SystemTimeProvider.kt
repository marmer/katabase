package io.github.marmer

import java.time.Clock
import java.time.LocalTime

class SystemTimeProvider(private val clock: Clock = Clock.systemDefaultZone()) {
    fun currentTime(): LocalTime = LocalTime.now(clock)
}
