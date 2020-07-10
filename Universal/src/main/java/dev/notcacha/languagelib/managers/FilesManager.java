package dev.notcacha.languagelib.managers;

import org.jetbrains.annotations.NotNull;

public interface FilesManager<C> {

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

    /**
     * Get a specific language file
     *
     * @param language, language name to obtain its respective file
     */

    C getFile(@NotNull String language);

    /**
     * Get the default language
     */

    C getDefaultFile();

}
