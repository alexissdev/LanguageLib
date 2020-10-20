package dev.notcacha.languagelib.bungee.loader;

import dev.notcacha.languagelib.bungee.file.BungeeLanguageFile;
import dev.notcacha.languagelib.exception.FileNotFoundException;
import dev.notcacha.languagelib.file.LanguageFile;
import dev.notcacha.languagelib.i18n.I18n;
import dev.notcacha.languagelib.i18n.message.Message;
import dev.notcacha.languagelib.loader.FileLoader;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BungeeFileLoader implements FileLoader {

    private final Plugin plugin;
    private final I18n i18n;
    private String format;

    public BungeeFileLoader(Plugin plugin, I18n i18n) {
        this.plugin = plugin;
        this.i18n = i18n;
        this.format = "language_%lang%.yml";
    }

    @Override
    public String getFormat() {
        return this.format;
    }

    @Override
    public FileLoader setFormat(String format) {
        this.format = format;
        return this;
    }

    @Override
    public LanguageFile load(String name, File folder) {
        String inputFile = format.replace("%lang%", name);

        InputStream inputStream = plugin.getResourceAsStream(inputFile);
        if (inputStream == null) {
            throw new FileNotFoundException("The file " + inputFile + " was not founded in plugin files");
        }

        return new BungeeLanguageFile(i18n, ConfigurationProvider.getProvider(YamlConfiguration.class).load(inputStream));
    }

    @Override
    public LanguageFile loadAndCreate(String name, File folder) {
        String inputFile = format.replace("%lang%", name);

        File file = new File(folder, inputFile);
        if (!file.exists()) {
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }

            InputStream inputStream = plugin.getResourceAsStream(inputFile);
            if (inputStream == null) {
                throw new FileNotFoundException("The file " + inputFile + " was not founded in plugin files");
            }

            Configuration inputConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(inputStream);
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(inputConfig, file);
            } catch (IOException exception) {
                throw new IllegalArgumentException(i18n.getMessage(Message.FILE_LOAD_ERROR.getId())
                        .replace("%file_name%", name), exception);
            }
        }

        return load(name, file);
    }
}
