package dev.notcacha.languagelib.managers;

import dev.notcacha.languagelib.file.LanguageFile;

import java.util.Map;

public interface FileManageable {

    /**
     * @return a {@link Map} with all languages and files set
     */

    Map<String, LanguageFile> get();

    /**
     * Add file from cache
     *
     * @param key name from language file
     */

    default void add(String key) {
        add(key, false);
    }

    /**
     * Add file from cache
     *
     * @param key name from language file
     * @param create this represents if the file has to be created or not, in all cases if it is 'true' the file will be created before being loaded otherwise it will only be loaded
     */

    void add(String key, boolean create);

    /**
     * @return an object {@link LanguageFile} using {@param key} to get it
     */

    default LanguageFile find(String key) {
        return get().get("language_" + key);
    }

    /**
     * @return if there is an object {@link LanguageFile} with the key {@param key}
     */

    default boolean exists(String key) {
        return get().containsKey("language_" + key);
    }


    /**
     * Remove file from cache
     *
     * @param key from get and delete object
     */

    default void remove(String key) {
        get().remove("language_" + key );
    }

    /**
     * @return the default file {@link LanguageFile}
     * */

    LanguageFile getDefault();
}
