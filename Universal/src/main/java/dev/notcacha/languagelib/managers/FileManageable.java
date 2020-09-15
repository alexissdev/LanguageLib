package dev.notcacha.languagelib.managers;

import dev.notcacha.languagelib.file.LanguageFile;

import java.util.Set;

public interface FileManageable {

    /**
     * @return a {@link Set} with all languages files
     */

    Set<LanguageFile> get();

    /**
     * Add file from cache
     *
     * @see this#add(String, boolean)
     */

    default void add(String key) {
        add(key, false);
    }

    /**
     * Add file
     *
     * @param key    name from language file
     * @param create this represents if the file has to be created or not, in all cases if it is 'true' the file will be created before being loaded otherwise it will only be loaded
     */

    void add(String key, boolean create);

    /**
     * @return an object {@link LanguageFile} using {@param key} to get it
     */

    LanguageFile find(String key);

    /**
     * @return if there is an object {@link LanguageFile} with the key {@param key}
     */

    boolean exists(String key);


    /**
     * Remove file from cache
     *
     * @param key from get and delete object
     */

    void remove(String key);

    /**
     * @return the default file {@link LanguageFile}
     */

    LanguageFile getDefault();
}
