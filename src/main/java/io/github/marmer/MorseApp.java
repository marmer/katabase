package io.github.marmer;

import javax.inject.Inject;

public class MorseApp {

  private final InputProvider inputProvider;
  private final OutputConsumer outputConsumer;
  private final MorseDecoder morseDecoder;

  public static void main(final String... args) {
    DaggerMorseAppFactory
        .create()
        .createMorseApp()
        .run();
  }

  @Inject
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
