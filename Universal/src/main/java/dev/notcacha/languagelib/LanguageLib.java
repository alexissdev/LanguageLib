package dev.notcacha.languagelib;

import dev.notcacha.languagelib.managers.FilesManager;
import dev.notcacha.languagelib.managers.TranslationManager;

/**
 * @param <C> represents the configuration file to be used, for example in Bukkit "YamlConfiguration"
 * is used and in BungeeCord "Configuration"
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
