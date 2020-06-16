package io.github.marmer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.mockito.Mockito.*;

class OcheTest {
    final GreetingProvider greetingProvider = mock(GreetingProvider.class);
    private Supplier<String> inputSupplier = mock(Supplier.class);
    private Consumer<String> outputConsumer = mock(Consumer.class);

    private Oche underTest = new Oche(inputSupplier, outputConsumer, greetingProvider);

    @Test
    @DisplayName("User input should be listed in reverse")
    void start_UserInputShouldBeListedInReverse()
            throws Exception {
        // Preparation
        when(inputSupplier.get()).thenReturn("Dog Cake", "Stop!");

        // Execution
        underTest.start("Superman");

        // Assertion
        verify(outputConsumer).accept("ekaC goD");
    }

    @Test
    @DisplayName("It should be possible to process more than one input")
    void start_ItShouldBePossibleToProcessMoreThanOneInput()
            throws Exception {
        // Preparation
        when(inputSupplier.get()).thenReturn("Dog Cake", "bone", "Stop!");

        // Execution
        underTest.start("Superman");

        // Assertion
        final var inOrder = inOrder(outputConsumer);
        inOrder.verify(outputConsumer).accept("ekaC goD");
        inOrder.verify(outputConsumer).accept("enob");
    }

    @Test
    @DisplayName("It should stop with a message and a username at the stop command")
    void start_ItShouldStopWithAMessageAndAUsernameAtTheStopCommand()
            throws Exception {
        // Preparation
        when(inputSupplier.get()).thenReturn("Stop!");

        // Execution
        underTest.start("Superman");

        // Assertion
        verify(outputConsumer).accept("Adios Superman");
    }

    @Test
    @DisplayName("It hould like palindromes after the 'reversed' output")
    void start_ItHouldLikePalindromes()
            throws Exception {
        // Preparation
        when(inputSupplier.get()).thenReturn("anna", "Stop!");

        // Execution
        underTest.start("Superman");

        // Assertion
        final var inOrder = inOrder(outputConsumer);
        inOrder.verify(outputConsumer).accept("anna");
        inOrder.verify(outputConsumer).accept("Bonita palabra!");
    }

    @Test
    @DisplayName("It should greet the user on start")
    void start_ItShouldGreetTheUserOnStart()
            throws Exception {
        // Preparation
        when(inputSupplier.get()).thenReturn("Stop!");
        when(greetingProvider.getGreetingFor("Superman")).thenReturn("Hello Superman");

        // Execution
        underTest.start("Superman");

        // Assertion
        verify(outputConsumer).accept("Hello Superman");
    }

}
