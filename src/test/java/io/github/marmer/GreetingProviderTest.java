package io.github.marmer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class GreetingProviderTest {
    private SystemTimeProvider systemTimeProvider = spy(SystemTimeProvider.class);

    private GreetingProvider underTest = new GreetingProvider(systemTimeProvider);


    private static Object[][] testData() {
        return new Object[][]{
                {LocalTime.of(6, 0), "John Doe", "Buenos Dias John Doe"},
                {LocalTime.of(11, 59, 59, 99999), "John Doe", "Buenos Dias John Doe"},
                {LocalTime.of(7, 2, 3, 45678), "John Doe", "Buenos Dias John Doe"},
                {LocalTime.of(12, 0), "John Doe", "Buenos Tarde John Doe"},
                {LocalTime.of(19, 59, 59, 99999), "John Doe", "Buenos Tarde John Doe"},
                {LocalTime.of(13, 2, 3, 45678), "John Doe", "Buenos Tarde John Doe"},
                {LocalTime.of(20, 0), "John Doe", "Buenos Noche John Doe"},
                {LocalTime.of(5, 59, 59, 99999), "John Doe", "Buenos Noche John Doe"},
                {LocalTime.of(0, 0), "John Doe", "Buenos Noche John Doe"},
                {LocalTime.of(7, 2, 3, 45678), "Superman", "Buenos Dias Superman"},
        };
    }

    @ParameterizedTest
    @MethodSource("testData")
    @DisplayName("It should greet correctly with the given name at the corret time")
    void getGreetingFor_ItShouldGreetCorrectlyWithTheGivenNameAtTheCorretTime(final LocalTime time, final String name, final String expectedGreeting)
            throws Exception {
        // Preparation
        doReturn(time).when(systemTimeProvider).getNow();

        // Execution
        final var result = underTest.getGreetingFor(name);

        // Assertion
        assertEquals(expectedGreeting, result);
    }
}
