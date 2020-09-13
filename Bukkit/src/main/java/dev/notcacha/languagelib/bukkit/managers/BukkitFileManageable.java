package dev.notcacha.languagelib.bukkit.managers;

import dev.notcacha.languagelib.file.LanguageFile;
import dev.notcacha.languagelib.loader.FileLoader;
import dev.notcacha.languagelib.managers.FileManageable;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BukkitFileManageable implements FileManageable {

    private final FileLoader fileLoader;
    private final Plugin plugin;

    private final Map<String, LanguageFile> fileMap;
    private final String defaultFile;

    public BukkitFileManageable(FileLoader fileLoader, Plugin plugin, String defaultLanguage) {
        this.fileLoader = fileLoader;
        this.plugin = plugin;

        this.defaultFile = defaultLanguage;
        this.fileMap = new HashMap<>();

        add(defaultLanguage);
    }

    @Override
    public Map<String, LanguageFile> get() {
        return this.fileMap;
    }

    @Override
    public void add(String key) {
        this.fileMap.put(key, fileLoader.load(key, plugin.getDataFolder()));
    }

    @Override
    public LanguageFile getDefault() {
        return this.get().get(defaultFile);
    }
}
