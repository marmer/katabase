package io.github.marmer;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class PlainMorseDecoder implements MorseDecoder {

  private final MorseDictionary morseDict;

  @Inject
  public PlainMorseDecoder(final MorseDictionary morseDict) {
    this.morseDict = morseDict;
  }

  @Override
  public String decode(final String s) {
    if (s == null || s.isBlank()) {
      return "";
    }
    return decodeLine(s);
  }

  @NotNull
  private String decodeLine(final String s) {
    return Stream.of(splitToWords(s))
        .map(this::decodeWord).collect(Collectors.joining(" "));
  }

  @NotNull
  private String decodeWord(final String word) {
    return Arrays.stream(splitToSymbols(word))
        .map(this::decodeSymbol)
        .collect(Collectors.joining(""));
  }

  private String decodeSymbol(final String symbol) {
    return morseDict.get(symbol).orElse("?");
  }

  @NotNull
  private String[] splitToSymbols(final String it) {
    return it.split(" ");
  }

  @NotNull
  private String[] splitToWords(final String s) {
    return s.split(" {3}");
  }
}
