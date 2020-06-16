package io.github.marmer;

import java.time.LocalTime;

public class GreetingProvider {

    public static final LocalTime MORNING_START = LocalTime.of(6, 0);
    public static final LocalTime NOON_START = LocalTime.of(12, 0);
    public static final LocalTime NIGHT_START = LocalTime.of(20, 0);
    private final SystemTimeProvider systemTimeProvider;

    public GreetingProvider(final SystemTimeProvider systemTimeProvider) {
        this.systemTimeProvider = systemTimeProvider;
    }

    public String getGreetingFor(final String name) {
        final LocalTime now = now();
        if (isMorning(now)) {
            return "Buenos Dias " + name;
        } else if (isNoon(now)) {
            return "Buenos Tarde " + name;
        } else {
            return "Buenos Noche " + name;
        }
    }

    private boolean isNoon(final LocalTime now) {
        return isBetween(now, NOON_START, NIGHT_START);
    }

    private boolean isMorning(final LocalTime now) {
        return isBetween(now, MORNING_START, NOON_START);
    }

    private boolean isBetween(final LocalTime now, final LocalTime startInclusive, final LocalTime endExclusive) {
        return (now.equals(startInclusive) || now.isAfter(startInclusive)) &&
                now.isBefore(endExclusive);
    }

    private LocalTime now() {
        return systemTimeProvider.getNow();
    }
}
