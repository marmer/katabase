package io.github.marmer;

import java.util.stream.Stream;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class ElectricSignalMorseDecoderDecorator implements MorseDecoder {

  private final MorseDecoder morseDecoder;

  @Inject
  public ElectricSignalMorseDecoderDecorator(final MorseDecoder morseDecoder) {
    this.morseDecoder = morseDecoder;
  }

  @Override
  public String decode(final String electricMorseSignal) {
    return morseDecoder.decode(plainMorseSignalOf(electricMorseSignal));
  }

  @NotNull
  private String plainMorseSignalOf(final String electricMorseSignal) {
    final var timing = timingOf(electricMorseSignal);

    return electricMorseSignal
        .replace("0000000".repeat(timing), "   ")
        .replace("000".repeat(timing), " ")
        .replace("111".repeat(timing), "-")
        .replace("1".repeat(timing), ".")
        .replace("0".repeat(timing), "");
  }

  private int timingOf(final String electricMorseSignal) {
    return Stream.of(electricMorseSignal.split("1+"))
        .map(String::length)
        .mapToInt(it -> it)
        .filter(it -> it > 0)
        .min()
        .getAsInt();
  }
}
