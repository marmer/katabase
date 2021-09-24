package io.github.marmer;

import dagger.Component;
import io.github.marmer.modules.InputProviderModule;
import io.github.marmer.modules.MorseDecoderModule;
import io.github.marmer.modules.MorseDictionaryModule;
import io.github.marmer.modules.OutputConsumerModule;

@Component(modules = {MorseDictionaryModule.class, InputProviderModule.class,
    OutputConsumerModule.class, MorseDecoderModule.class})
public interface MorseAppFactory {

  MorseApp createMorseApp();
}
