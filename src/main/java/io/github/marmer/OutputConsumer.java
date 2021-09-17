package io.github.marmer;

@FunctionalInterface
public interface OutputConsumer {

  void take(String output);
}
