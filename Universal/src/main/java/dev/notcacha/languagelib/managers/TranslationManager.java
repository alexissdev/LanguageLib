package dev.notcacha.languagelib.managers;

import dev.notcacha.languagelib.message.TranslatableMessage;

public interface TranslationManager {

    /**
     * Returns a class {@link TranslatableMessage}, so that we can get the message in a certain language and set variables
     *
     * @param path, path to be obtained from the specified language file
     */

    TranslatableMessage getTranslation(String path);

}
