package dev.notcacha.languagelib.bungee;

import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.bungee.managers.BungeeFileManager;
import dev.notcacha.languagelib.bungee.managers.BungeeTranslateManager;
import dev.notcacha.languagelib.managers.FilesManager;
import dev.notcacha.languagelib.managers.TranslationManager;
import net.md_5.bungee.config.Configuration;

public class BungeeLanguageLib<C extends Configuration> implements LanguageLib<C> {

    private final TranslationManager translateManager;
    private final FilesManager<C> filesManager;

    public BungeeLanguageLib(C defaultFile) {
        this.filesManager = new BungeeFileManager<>(defaultFile);
        this.translateManager = new BungeeTranslateManager(this);
    }

    @Override
    public TranslationManager getTranslationManager() {
        return this.translateManager;
    }

    @Override
    public FilesManager<C> getFileManager() {
        return this.filesManager;
    }
}
