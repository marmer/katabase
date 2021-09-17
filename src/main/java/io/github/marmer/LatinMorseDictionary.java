package io.github.marmer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LatinMorseDictionary implements MorseDictionary {

  private final Map<String, String> morseDict =
      new HashMap<>() {
        private static final long serialVersionUID = -1436510038482402706L;

        {
          put(".-", "A");
          put("-...", "B");
          put("-.-.", "C");
          put("-..", "D");
          put(".", "E");
          put("..-.", "F");
          put("--.", "G");
          put("....", "H");
          put("..", "I");
          put(".---", "J");
          put("-.-", "K");
          put(".-..", "L");
          put("--", "M");
          put("-.", "N");
          put("---", "O");
          put(".--.", "P");
          put("--.-", "Q");
          put(".-.", "R");
          put("...", "S");
          put("-", "T");
          put("..-", "U");
          put("...-", "V");
          put(".--", "W");
          put("-..-", "X");
          put("-.--", "Y");
          put("--..", "Z");
          put("-----", "0");
          put(".----", "1");
          put("..---", "2");
          put("...--", "3");
          put("....-", "4");
          put(".....", "5");
          put("-....", "6");
          put("--...", "7");
          put("---..", "8");
          put("----.", "9");
          put(".-.-.-", ".");
          put("--..--", ",");
          put("..--..", "?");
          put(".----.", "'");
          put("-.-.--", "!");
          put("-..-.", "/");
          put("-.--.", "(");
          put("-.--.-", ")");
          put(".-...", "&");
          put("---...", ":");
          put("-.-.-.", ";");
          put("-...-", "=");
          put(".-.-.", "+");
          put("-....-", "-");
          put("..--.-", "_");
          put("...-..-", "$");
          put(".--.-.", "@");
        }
      };


  @Override
  public Optional<String> get(final String key) {
    return Optional.ofNullable(morseDict.get(key));
  }
}
