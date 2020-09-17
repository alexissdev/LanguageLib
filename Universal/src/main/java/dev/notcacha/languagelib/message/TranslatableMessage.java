package dev.notcacha.languagelib.message;

import dev.notcacha.languagelib.placeholder.PlaceholderApplier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TranslatableMessage {

    /**
     * @return path of message
     */

    @NotNull
    String getPath();

    /**
     * Get message from path
     *
     * @param language the language in which to search for the message
     */

    String getMessage(String language);

    /**
     * Get message from path
     *
     * @param language the language in which to search for the message
     */

    List<String> getMessages(String language);

    /**
     * Set variables to message
     *
     * @param key   message to replace
     * @param value to be set in the {@param key}
     */

    TranslatableMessage setVariable(String key, String value);

    /**
     * It is the same as the method above, only that the {@param value} receives a valueOf towards a {@link String}
     */

    default TranslatableMessage setVariable(String key, Number value) {
        return setVariable(key, String.valueOf(value));
    }

    /**
     * Set a {@link PlaceholderApplier} to do set in the message, {@param placeholder} will be the one set to use
     */

    <T> TranslatableMessage addPlaceholder(T holder, PlaceholderApplier placeholder);

    /**
     * Add multiple {@link PlaceholderApplier} to be applied to the message
     */

    default <T> TranslatableMessage addPlaceholders(T holder, PlaceholderApplier... placeholders) {
        for (PlaceholderApplier placeholderApplier : placeholders) {
            addPlaceholder(holder, placeholderApplier);
        }
        return this;
    }

    /**
     * Set color from message
     */

    TranslatableMessage colorize();

}
