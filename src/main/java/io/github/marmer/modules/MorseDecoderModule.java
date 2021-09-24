package io.github.marmer.modules;

import dagger.Module;
import dagger.Provides;
import io.github.marmer.ElectricSignalMorseDecoderDecorator;
import io.github.marmer.LatinMorseDictionary;
import io.github.marmer.MorseDecoder;
import io.github.marmer.PlainMorseDecoder;

@Module
public abstract class MorseDecoderModule {

  @Provides
  static MorseDecoder createMorseDecoder(
      final PlainMorseDecoder morseDecoder, final LatinMorseDictionary latinMorseDictionary) {
    return new ElectricSignalMorseDecoderDecorator(
        new PlainMorseDecoder(new LatinMorseDictionary()));
  }

}
