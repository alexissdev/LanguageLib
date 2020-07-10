package dev.notcacha.languagelib.bukkit;

import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.bukkit.managers.BukkitFileManager;
import dev.notcacha.languagelib.bukkit.managers.BukkitTranslateManager;
import dev.notcacha.languagelib.managers.FilesManager;
import dev.notcacha.languagelib.managers.TranslationManager;
import org.bukkit.configuration.Configuration;


public class BukkitLanguageLib<C extends Configuration> implements LanguageLib<C> {

    private final TranslationManager translateManager;
    private final FilesManager<C> filesManager;

    public BukkitLanguageLib(C defaultFile) {
        this.filesManager = new BukkitFileManager<>(defaultFile);
        this.translateManager = new BukkitTranslateManager(this);
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
