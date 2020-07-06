package dev.notcacha.languagelib.message;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TranslatableMessage {

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
     * Get message from path
     *
     * @param language, the language in which to search for the message
     */

    @NotNull
    List<String> getMessages(@NotNull String language);

    /**
     * Set variables to message
     *
     * @param key, message to replace
     * @param value, to be set in the {@param key}
     */

    @NotNull
    TranslatableMessage setVariable(@NotNull String key, @NotNull String value);

}
