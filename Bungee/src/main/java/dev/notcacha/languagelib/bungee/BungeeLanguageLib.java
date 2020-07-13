package dev.notcacha.languagelib.bungee;

import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.bungee.managers.BungeeFileManager;
import dev.notcacha.languagelib.bungee.managers.BungeeTranslateManager;
import dev.notcacha.languagelib.managers.FilesManager;
import dev.notcacha.languagelib.managers.TranslationManager;
import net.md_5.bungee.config.Configuration;

public class BungeeLanguageLib implements LanguageLib<Configuration> {

    private final TranslationManager translateManager;
    private final FilesManager<Configuration> filesManager;

    public BungeeLanguageLib(String defaultLanguage, Configuration defaultFile) {
        this.filesManager = new BungeeFileManager(defaultLanguage, defaultFile);
        this.translateManager = new BungeeTranslateManager(this);
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
