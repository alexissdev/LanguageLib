package dev.notcacha.languagelib.bukkit.managers;

import dev.notcacha.languagelib.file.LanguageFile;
import dev.notcacha.languagelib.loader.FileLoader;
import dev.notcacha.languagelib.managers.FileManageable;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BukkitFileManageable implements FileManageable {

    private final FileLoader fileLoader;
    private final File folder;

    private final Map<String, LanguageFile> fileMap;
    private final String defaultFile;

    public BukkitFileManageable(FileLoader fileLoader, Plugin plugin, String defaultLanguage) {
        this(fileLoader, plugin.getDataFolder(), defaultLanguage, false);
    }

    public BukkitFileManageable(FileLoader fileLoader, File folder, String defaultLanguage) {
        this(fileLoader, folder, defaultLanguage, false);
    }

    public BukkitFileManageable(FileLoader fileLoader, File folder, String defaultLanguage, boolean createDefaultFile) {
        this.fileLoader = fileLoader;
        this.folder = folder;

        this.defaultFile = defaultLanguage;
        this.fileMap = new HashMap<>();

        add(defaultLanguage, createDefaultFile);
    }

    @Override
    public Set<LanguageFile> get() {
        return new HashSet<>(this.fileMap.values());
    }

    @Override
    public void add(String key, boolean create) {
        this.fileMap.put(key, (!create) ? fileLoader.loadAndCreate(key, folder) : fileLoader.loadAndCreate(key, folder));
    }

    @Override
    public LanguageFile find(String key) {
        return this.fileMap.get(fileLoader.getFormat().replace("%lang%", key));
    }

    @Override
    public boolean exists(String key) {
        return this.fileMap.containsKey(fileLoader.getFormat().replace("%lang%", key));
    }

    @Override
    public void remove(String key) {
        this.fileMap.remove(fileLoader.getFormat().replace("%lang%", key));
    }

    @Override
    public LanguageFile getDefault() {
        return this.fileMap.get(defaultFile);
    }
}
