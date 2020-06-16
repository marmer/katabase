package io.github.marmer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Oche {
    public static final String STOP_COMMAND = "Stop!";
    private final Supplier<String> inputSupplier;
    private final Consumer<String> outputConsumer;
    private final GreetingProvider greetingProvider;

    public Oche(final Supplier<String> inputSupplier, final Consumer<String> outputConsumer, final GreetingProvider greetingProvider) {
        this.inputSupplier = inputSupplier;
        this.outputConsumer = outputConsumer;
        this.greetingProvider = greetingProvider;
    }

    public void start(final String name) {
        greet(name);

        String originalInput = inputSupplier.get();

        do {
            process(originalInput, name);
        } while (!(originalInput = inputSupplier.get()).equals(STOP_COMMAND));
    }

    private void greet(final String name) {
        outputConsumer.accept(greetingProvider.getGreetingFor(name));
    }

    private void process(final String originalInput, final String name) {
        final var reverseInput = reverseOf(originalInput);

        if (STOP_COMMAND.equals(originalInput)) {
            outputConsumer.accept("Adios " + name);
        } else {
            outputConsumer.accept(reverseInput);

            final boolean inputIsAnagram = reverseInput.equals(originalInput);
            if (inputIsAnagram) {
                outputConsumer.accept("Bonita palabra!");
            }
        }
    }

    private String reverseOf(final String input) {
        return new StringBuilder(input).reverse().toString();
    }

}
