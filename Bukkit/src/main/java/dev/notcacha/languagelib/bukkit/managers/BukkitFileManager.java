package dev.notcacha.languagelib.bukkit.managers;

import dev.notcacha.languagelib.managers.FilesManager;
import org.bukkit.configuration.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BukkitFileManager implements FilesManager<Configuration> {

    private final String defaultLanguage;
    private final Map<String, Configuration> files;

    public BukkitFileManager(String defaultLanguage, Configuration defaultFile) {
        this.files = new ConcurrentHashMap<>();
        this.defaultLanguage = defaultLanguage.toLowerCase();
        this.addFile(defaultLanguage, defaultFile);
    }

    @Override
    public boolean containsFile(@NotNull String language) {
        return this.files.containsKey(language.toLowerCase());
    }

    @Override
    public void addFile(@NotNull String language, @NotNull Configuration configuration) {
        if (!containsFile(language.toLowerCase())) {
            this.files.put(language.toLowerCase(), configuration);
        }
    }

    @Override
    public void removeFile(@NotNull String language) {
        if (containsFile(language.toLowerCase())) {
            this.files.remove(language.toLowerCase());
        }
    }

    @Override
    public Configuration getFile(@NotNull String language) {
        return this.files.get(language.toLowerCase());
    }

    @Override
    public Configuration getDefaultFile() {
        return this.files.get(defaultLanguage.toLowerCase());
    }
}
