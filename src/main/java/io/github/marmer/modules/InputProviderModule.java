package io.github.marmer.modules;

import dagger.Module;
import dagger.Provides;
import io.github.marmer.InputProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Module
public abstract class InputProviderModule {

  @Provides
  static InputProvider createInputProvider() {
    return () -> {
      try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
        return reader.readLine();
      } catch (final IOException e) {
        throw new RuntimeException(e);
      }
    };
  }
}
