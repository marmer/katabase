package io.github.marmer;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public class ZeilenFormatter {

    @NotNull
    public static String umbrechen(@NotNull final String text,
        final int maxLineLength) {
        return toLinesWithMaxLength(text.replaceAll("\\s+", " ").trim(), maxLineLength)
            .map(String::trim)
            .collect(Collectors.joining("\n"));
    }

    private static Stream<String> toLinesWithMaxLength(final String text, final int maxLineLength) {
        final var currentLine = text.substring(0, Math.min(maxLineLength, text.length()));
        final var rest = text.replaceFirst(Pattern.quote(currentLine), "");
        return rest.isEmpty() ?
            Stream.of(currentLine) :
            Stream.concat(Stream.of(currentLine), toLinesWithMaxLength(rest, maxLineLength));
    }
}
