package io.github.marmer;

import java.util.Optional;

@FunctionalInterface
public interface MorseDictionary {

  Optional<String> get(String key);
}
