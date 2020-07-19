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

    String getMessage(String language);

    /**
     * Get message from path
     *
     * @param language, the language in which to search for the message
     */

    List<String> getMessages(String language);

    /**
     * Set variables to message
     *
     * @param key, message to replace
     * @param value, to be set in the {@param key}
     */

    TranslatableMessage setVariable(String key, String value);

    /**
     * Set color from message
     * @param setColor boolean value
     * */

    TranslatableMessage setColor(boolean setColor);

}
