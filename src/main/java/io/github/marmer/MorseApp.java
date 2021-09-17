package io.github.marmer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MorseApp {

  private final InputProvider inputProvider;
  private final OutputConsumer outputConsumer;
  private final MorseDecoder morseDecoder;

  public static void main(final String... args) {
    final var reader = new BufferedReader(new InputStreamReader(System.in));
    new MorseApp(() -> {
      try {
        return reader.readLine();
      } catch (final IOException e) {
        throw new RuntimeException(e);
      }
    }, System.out::println, new MorseDecoder(new LatinMorseDictionary())).run();
  }

  public MorseApp(final InputProvider inputProvider, final OutputConsumer outputConsumer,
      final MorseDecoder morseDecoder) {
    this.inputProvider = inputProvider;
    this.outputConsumer = outputConsumer;
    this.morseDecoder = morseDecoder;
  }

  void run() {
    outputConsumer.take(morseDecoder.decode(inputProvider.getInput()));
  }

}
