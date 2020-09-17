package dev.notcacha.languagelib.bukkit;

import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.bukkit.loader.BukkitFileLoader;
import dev.notcacha.languagelib.i18n.DefaultI18n;
import dev.notcacha.languagelib.i18n.I18n;
import dev.notcacha.languagelib.loader.FileLoader;
import dev.notcacha.languagelib.managers.FileManageable;
import dev.notcacha.languagelib.managers.SimpleFileManageable;
import dev.notcacha.languagelib.managers.SimpleTranslationManager;
import dev.notcacha.languagelib.managers.TranslationManager;
import org.bukkit.plugin.Plugin;

public class BukkitLanguageLib implements LanguageLib {

    private final TranslationManager translationManager;
    private final FileManageable fileManageable;
    private final FileLoader fileLoader;
    private final I18n i18n;

    public BukkitLanguageLib(TranslationManager translationManager, FileManageable fileManageable, FileLoader fileLoader, I18n i18n) {
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
            this(plugin, defaultLanguage, false);
        }

        public Builder(Plugin plugin, String defaultLanguage, boolean createDefaultFile) {
            this.i18n = new DefaultI18n();
            this.fileLoader = new BukkitFileLoader(plugin, i18n);
            this.fileManageable = new SimpleFileManageable(fileLoader, plugin.getDataFolder(), defaultLanguage, createDefaultFile);
            this.translationManager = new SimpleTranslationManager(fileManageable);
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
            return new BukkitLanguageLib(translationManager, fileManageable, fileLoader, i18n);
        }
    }

    /**
     * @see this#builder(Plugin, String, boolean)
     */

    public static LanguageLib.Builder builder(Plugin plugin, String defaultLanguage) {
        return builder(plugin, defaultLanguage, false);
    }

    /**
     * @param plugin from your plugin
     * @param defaultLanguage file name
     * @param createDefaultFile whether the value of this parameter will decide if the file has to be created before loading it or not
     * @return builder from {@link BukkitLanguageLib}
     */

    public static LanguageLib.Builder builder(Plugin plugin, String defaultLanguage, boolean createDefaultFile) {
        return new BukkitLanguageLib.Builder(plugin, defaultLanguage, createDefaultFile);
    }
}
