package dev.notcacha.languagelib;

import dev.notcacha.languagelib.message.TranslateMessage;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface TranslateManager<C> {

    /**
     * Returns a class "TranslateMessage", so that we can get the message in a certain language and set variables
     *
     * @param path, path to be obtained from the specified language file
     */

    Optional<TranslateMessage> getTranslate(@NotNull String path);

    /**
     * Returns if there is a file of a certain language
     *
     * @param language, language file name
     */

    boolean containsFile(@NotNull String language);

    /**
     * Add file
     *
     * @param language, language has been added
     * @param configuration, language file has been added
     */

    void addFile(@NotNull String language, @NotNull C configuration);

    /**
     * Remove file
     *
     * @param language, file language name has been removed
     */

    void removeFile(@NotNull String language);

}
