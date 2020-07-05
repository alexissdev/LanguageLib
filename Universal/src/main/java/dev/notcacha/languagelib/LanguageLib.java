package dev.notcacha.languagelib;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @param <C>represents the configuration file to be used, for example in Bukkit "YamlConfiguration"
 * is used and in BungeeCord "Configuration"
 * */

public interface LanguageLib<C> {

    /**
     * Get all languages files
     */

    Map<String, C> getFiles();

    /**
     * Get a specific language file
     *
     * @param language, language name to obtain its respective file
     */

    C getFile(@NotNull String language);

    /**
     * The class that is responsible for creating messages and files
     */

    TranslateManager<C> getTranslateManager();

    /**
     * Get the default language
     */

    C getDefaultFile();

}
