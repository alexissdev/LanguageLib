package dev.notcacha.languagelib.managers;

import dev.notcacha.languagelib.message.TranslatableMessage;

import java.util.Optional;

public interface TranslationManager {

    /**
     * Returns a class "TranslatableMessage", so that we can get the message in a certain language and set variables
     *
     * @param path, path to be obtained from the specified language file
     */

    Optional<TranslatableMessage> getTranslation(String path);

}
