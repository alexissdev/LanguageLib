package dev.notcacha.languagelib.bungee.loader;

import dev.notcacha.languagelib.bungee.file.BungeeLanguageFile;
import dev.notcacha.languagelib.exception.FileNotFoundException;
import dev.notcacha.languagelib.file.LanguageFile;
import dev.notcacha.languagelib.i18n.I18n;
import dev.notcacha.languagelib.i18n.message.Message;
import dev.notcacha.languagelib.loader.FileLoader;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BungeeFileLoader implements FileLoader {

    private final I18n i18n;
    private String format;

    public BungeeFileLoader(I18n i18n) {
        this.i18n = i18n;
        this.format = "language_%lang%.yml";
    }

    @Override
    public FileLoader setFormat(String format) {
        this.format = format;
        return this;
    }

    @Override
    public LanguageFile load(String name, File folder) {
        File file = new File(folder, format.replace("%lang%", name));
        if (!file.exists()) {
            throw new FileNotFoundException(i18n.getMessage(Message.FILE_NOT_FOUND).replace("%file_name%", name));
        }

        try {
            return new BungeeLanguageFile(i18n, ConfigurationProvider.getProvider(YamlConfiguration.class).load(file));
        } catch (IOException exception) {
            throw new IllegalArgumentException(i18n.getMessage(Message.FILE_LOAD_ERROR).replace("%file_name%", name), exception);
        }
    }
}
