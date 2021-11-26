package io.github.marmer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MorseAppIT {

  private MorseApp underTest;

  @Mock
  private InputProvider inputProvider;
  @Mock
  private OutputConsumer outputConsumer;

  @BeforeEach
  void setUp() {
    underTest = new MorseApp(inputProvider, outputConsumer,
        new ElectricSignalMorseDecoderDecorator(
            new PlainMorseDecoder(
                new LatinMorseDictionary()))); // TODO: marmer 17.09.2021 get it by a factory

//    https://medium.com/@fabioCollini/android-testing-using-dagger-2-mockito-and-a-custom-junit-rule-c8487ed01b56
  }

  @Test
  @DisplayName("User Inputs can be translated")
  @SneakyThrows
  void run_UserInputsCanBeTranslated() {
    // Preparation
    when(inputProvider.getInput()).thenReturn("-.. . .-.   -- --- .--. ...");

    // Execution
    underTest.run();

    // Assertion
    verify(outputConsumer).take("DER MOPS");
  }

  @Test
  @DisplayName("Electric Signals can be translated")
  @SneakyThrows
  void run_ElectricSignalsCanBeTranslated() {
    // Preparation
    when(inputProvider.getInput()).thenReturn(
        "11101010001000101110100000001110111000111011101110001011101110100010101");

    // Execution
    underTest.run();

    // Assertion
    verify(outputConsumer).take("DER MOPS");
  }

  @Test
  @DisplayName("Electric Signals can be translated with a timing of 3")
  @SneakyThrows
  void run_ElectricSignalsCanBeTranslatedWithATimingOf3() {
    // Preparation
    when(inputProvider.getInput()).thenReturn(
        "111111111000111000111000000000111000000000111000111111111000111000000000000000000000111111111000111111111000000000111111111000111111111000111111111000000000111000111111111000111111111000111000000000111000111000111");

    // Execution
    underTest.run();

    // Assertion
    verify(outputConsumer).take("DER MOPS");
  }
}
