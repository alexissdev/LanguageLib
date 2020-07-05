package dev.notcacha.languagelib.message;

import org.jetbrains.annotations.NotNull;

public interface TranslateMessage {

    /**
     * Get path of message
     */

    @NotNull
    String getPath();

    /**
     * Get message from path
     *
     * @param language, the language in which to search for the message
     */

    @NotNull
    String getMessage(@NotNull String language);

    /**
     * Set variables to message
     *
     * @param key, message to replace
     * @param value, to be set in the {@param key}
     */

    @NotNull
    TranslateMessage setVariable(@NotNull String key, @NotNull String value);

}
