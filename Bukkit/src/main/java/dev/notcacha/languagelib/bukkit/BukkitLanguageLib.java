package dev.notcacha.languagelib.bukkit;

import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.bukkit.managers.BukkitFileManager;
import dev.notcacha.languagelib.bukkit.managers.BukkitTranslateManager;
import dev.notcacha.languagelib.managers.FilesManager;
import dev.notcacha.languagelib.managers.TranslationManager;
import org.bukkit.configuration.Configuration;

public class BukkitLanguageLib implements LanguageLib<Configuration> {

    private final TranslationManager translateManager;
    private final FilesManager<Configuration> filesManager;

    public BukkitLanguageLib(String defaultLanguage, Configuration defaultFile) {
        this.filesManager = new BukkitFileManager(defaultLanguage, defaultFile);
        this.translateManager = new BukkitTranslateManager(this);
    }

    @Override
    public TranslationManager getTranslationManager() {
        return this.translateManager;
    }

    @Override
    public FilesManager<Configuration> getFileManager() {
        return this.filesManager;
    }
}
