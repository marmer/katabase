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
        new MorseDecoder(
            new LatinMorseDictionary())); // TODO: marmer 17.09.2021 get it by a factory
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
}
