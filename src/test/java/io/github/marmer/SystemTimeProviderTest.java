package io.github.marmer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class SystemTimeProviderTest {
    private SystemTimeProvider underTest = new SystemTimeProvider();

    @Test
    @DisplayName("it should provide the current time")
    void getNow_ItShouldProvideTheCurrentTime()
            throws Exception {
        // Preparation
        final var before = LocalTime.now();
        // Execution
        final var result = underTest.getNow();

        // Assertion
        final var after = LocalTime.now();

        assertThat(result, is(betweenInclusive(before, after)));
    }

    private Matcher<LocalTime> betweenInclusive(final LocalTime before, final LocalTime after) {
        return new TypeSafeMatcher<LocalTime>() {
            @Override
            protected boolean matchesSafely(final LocalTime item) {
                return item.equals(before) || item.equals(after) || (before.isBefore(item) && item.isBefore(after));
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("Expected a time between ").appendValue(before).appendText(" and ").appendValue(after);
            }
        };
    }
}
