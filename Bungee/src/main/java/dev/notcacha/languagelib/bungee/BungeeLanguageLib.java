package dev.notcacha.languagelib.bungee;

import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.bungee.loader.BungeeFileLoader;
import dev.notcacha.languagelib.bungee.managers.BungeeFileManageable;
import dev.notcacha.languagelib.bungee.managers.BungeeTranslationManager;
import dev.notcacha.languagelib.i18n.DefaultI18n;
import dev.notcacha.languagelib.i18n.I18n;
import dev.notcacha.languagelib.loader.FileLoader;
import dev.notcacha.languagelib.managers.FileManageable;
import dev.notcacha.languagelib.managers.TranslationManager;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeLanguageLib implements LanguageLib {

    private final TranslationManager translationManager;
    private final FileManageable fileManageable;
    private final FileLoader fileLoader;
    private final I18n i18n;

    public BungeeLanguageLib(TranslationManager translationManager, FileManageable fileManageable, FileLoader fileLoader, I18n i18n) {
        this.translationManager = translationManager;
        this.fileManageable = fileManageable;
        this.fileLoader = fileLoader;
        this.i18n = i18n;
    }

    @Override
    public TranslationManager getTranslationManager() {
        return this.translationManager;
    }

    @Override
    public FileManageable getFileManageable() {
        return this.fileManageable;
    }

    @Override
    public FileLoader getFileLoader() {
        return this.fileLoader;
    }

    @Override
    public I18n getI18n() {
        return this.i18n;
    }

    private static class Builder implements LanguageLib.Builder {

        private TranslationManager translationManager;
        private FileManageable fileManageable;
        private FileLoader fileLoader;
        private I18n i18n;

        public Builder(Plugin plugin, String defaultLanguage) {
            this.i18n = new DefaultI18n();
            this.fileLoader = new BungeeFileLoader(i18n);
            this.fileManageable = new BungeeFileManageable(fileLoader, plugin, defaultLanguage);
            this.translationManager = new BungeeTranslationManager(fileManageable);
        }

        @Override
        public LanguageLib.Builder setTranslationManager(TranslationManager translationManager) {
            this.translationManager = translationManager;
            return this;
        }

        @Override
        public LanguageLib.Builder setFilesManageable(FileManageable filesManageable) {
            this.fileManageable = filesManageable;
            return this;
        }

        @Override
        public LanguageLib.Builder setFileLoader(FileLoader fileLoader) {
            this.fileLoader = fileLoader;
            return this;
        }

        @Override
        public LanguageLib.Builder setI18n(I18n i18n) {
            this.i18n = i18n;
            return this;
        }

        @Override
        public LanguageLib build() {
            return new BungeeLanguageLib(translationManager, fileManageable, fileLoader, i18n);
        }
    }

    public static LanguageLib.Builder builder(Plugin plugin, String defaultLanguage) {
        return new Builder(plugin, defaultLanguage);
    }
}
