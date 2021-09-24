package io.github.marmer.modules;

import dagger.Module;
import dagger.Provides;
import io.github.marmer.OutputConsumer;

@Module
public abstract class OutputConsumerModule {

  @Provides
  static OutputConsumer createOutputConsumer() {
    return System.out::println;
  }

}
