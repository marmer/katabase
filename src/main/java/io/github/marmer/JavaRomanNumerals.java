package io.github.marmer;

import lombok.Value;

import java.util.Arrays;

public class JavaRomanNumerals {
    private static final NumberMapping[] NUMBER_MAPPINGS = {
            new NumberMapping(900, "CM"),
            new NumberMapping(1000, "M"),
            new NumberMapping(400, "CD"),
            new NumberMapping(500, "D"),
            new NumberMapping(90, "XC"),
            new NumberMapping(100, "C"),
            new NumberMapping(40, "XL"),
            new NumberMapping(50, "L"),
            new NumberMapping(9, "IX"),
            new NumberMapping(10, "X"),
            new NumberMapping(4, "IV"),
            new NumberMapping(5, "V"),
            new NumberMapping(1, "I")
    };

    static String toRomanNumber(final int decimalNumber) {
        return Arrays.stream(NUMBER_MAPPINGS)
                .sorted(JavaRomanNumerals::reverseByDecimal)
                .filter(numberMapping -> decimalNumber >= numberMapping.decimal)
                .findFirst()
                .map(numberMapping -> numberMapping.getRoman() + toRomanNumber(decimalNumber - numberMapping.getDecimal()))
                .orElse(""); // break condition
    }

    public static int toDecimalNumber(final String romanNumber) {
        return Arrays.stream(NUMBER_MAPPINGS)
                .filter(numberMapping -> romanNumber.toUpperCase().contains(numberMapping.getRoman()))
                .findFirst()
                .map(numberMapping -> numberMapping.getDecimal() + toDecimalNumber(romanNumber.toUpperCase().replaceFirst(numberMapping.getRoman(), "")))
                .orElse(0); // break condition
    }

    private static int reverseByDecimal(final NumberMapping o1, final NumberMapping o2) {
        return o2.decimal - o1.decimal;
    }

    @Value
    public static class NumberMapping {
        int decimal;
        String roman;
    }
}
