package dev.notcacha.languagelib;

import dev.notcacha.languagelib.managers.FilesManager;
import dev.notcacha.languagelib.managers.TranslationManager;

/**
 * @param <C> represents the class called "Configuration" from the Spigot and BungeeCord library
 */

public interface LanguageLib<C> {

    /**
     * The class that is responsible for creating messages and files
     */

    TranslationManager getTranslationManager();

    /**
     * Get File Manager
     */

    FilesManager<C> getFileManager();


}
