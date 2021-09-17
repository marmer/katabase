package io.github.marmer;

import static com.google.common.truth.Truth.assert_;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MorseDecoderTest {

  private final MorseDecoder underTest = new MorseDecoder(new LatinMorseDictionary());

  private static Object[][] translations() {
    return new Object[][]{
        {"-.-", "k"},
        {"..-. --- ---", "foo"},
        {"..-. --- ---   -... .- .-.", "FOO BAR"},
        {"..-. --- ---   abc   --.--... .- .-.", "FOO ? ?AR"},
        {"", ""},
        {"   ", ""},
        {null, ""}
    };
  }

  @ParameterizedTest(name = "[{index}] \"{0}\" should translate to \"{1}\"")
  @DisplayName("Translation should be possible")
  @SneakyThrows
  @MethodSource("translations")
  void decode_TranslationShouldBePossible(final String morse,
      final String expectedTranslation) {
    // Preparation

    // Execution
    final var result = underTest.decode(morse);

    // Assertion
    assert_().that(result).ignoringCase().isEqualTo(expectedTranslation);
  }

}
