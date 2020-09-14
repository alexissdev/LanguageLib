package dev.notcacha.languagelib.loader;

import dev.notcacha.languagelib.file.LanguageFile;

import java.io.File;
import java.io.IOException;

public interface FileLoader {

    /**
     * {@param format} will be the one established when looking for the file to load it
     */

    FileLoader setFormat(String format);

    /**
     * Load a file {@link LanguageFile}, {@param name} name of the file to be identified and loaded
     *
     * @param folder from file
     * @return a file {@link LanguageFile} fully ready to use
     */

    LanguageFile load(String name, File folder);

    /**
     * This method is almost the same as the previous method, the only difference is that in any case that the file does not exist, it will create it
     *
     * @see this#load(String, File);
     */

    LanguageFile loadAndCreate(String name, File folder);

}
