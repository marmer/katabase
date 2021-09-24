package io.github.marmer.modules;

import dagger.Binds;
import dagger.Module;
import io.github.marmer.LatinMorseDictionary;
import io.github.marmer.MorseDictionary;

@Module
public abstract class MorseDictionaryModule {

  @Binds
  abstract MorseDictionary morseDictionary(LatinMorseDictionary latinMorseDictionary);
}
