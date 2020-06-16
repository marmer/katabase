package io.github.marmer;

import java.time.LocalTime;

public class SystemTimeProvider {
    public LocalTime getNow() {
        return LocalTime.now();
    }
}
