package io.github.marmer;

import static com.google.common.truth.Truth.assert_;
import static org.mockito.Mockito.when;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ElectricSignalMorseDecoderDecoratorTest {

  @InjectMocks
  private ElectricSignalMorseDecoderDecorator underTest;

  @Mock
  private MorseDecoder morseDecoder;

  @Test
  @DisplayName("all non binary signals should be kept")
  @SneakyThrows
  void decode_AllNonBinarySignalsShouldBeKept() {
    // Preparation
    when(morseDecoder.decode("-..- .--.")).thenReturn("XP");

    // Execution
    final var result = underTest.decode("-..- .--.");

    // Assertion
    assert_().that(result).isEqualTo("XP");
  }

  @Test
  @DisplayName("Should forward decoded electrical signals")
  @SneakyThrows
  void decode_ShouldForwardDecodedElectricalSignals() {
    // Preparation
    when(morseDecoder.decode("-.. . .-.   -- --- .--. ...")).thenReturn("Der Mops");

    // Execution
    final var result = underTest.decode(
        "11101010001000101110100000001110111000111011101110001011101110100010101");

    // Assertion
    assert_().that(result).isEqualTo("Der Mops");
  }

  @Test
  @DisplayName("Should forward decoded electrical signals with a timing of 4")
  @SneakyThrows
  void decode_ShouldForwardDecodedElectricalSignalsWithATiningOf4() {
    // Preparation
    when(morseDecoder.decode("-.. . .-.   -- --- .--. ...")).thenReturn("Der Mops");

    // Execution
    final var result = underTest.decode(
        "11111111111100001111000011110000000000001111000000000000111100001111111111110000111100000000000000000000000000001111111111110000111111111111000000000000111111111111000011111111111100001111111111110000000000001111000011111111111100001111111111110000111100000000000011110000111100001111");

    // Assertion
    assert_().that(result).isEqualTo("Der Mops");
  }
}
