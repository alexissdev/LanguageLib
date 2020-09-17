package dev.notcacha.languagelib.placeholder;

@FunctionalInterface
public interface PlaceholderApplier {

    /**
     * Apply multiple variables in 1 single method to text {@param text}, using {@param holder} to get some value to be set
     */

    <T> String apply(T holder, String text);
}
