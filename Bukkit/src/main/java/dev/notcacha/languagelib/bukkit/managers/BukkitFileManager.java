package dev.notcacha.languagelib.bukkit.managers;

import dev.notcacha.languagelib.managers.FilesManager;
import org.bukkit.configuration.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BukkitFileManager<C extends Configuration> implements FilesManager<C> {

    private final C defaultFile;
    private final Map<String, C> files;

    public BukkitFileManager(C defaultFile) {
        this.defaultFile = defaultFile;
        this.files = new ConcurrentHashMap<>();
    }

    @Override
    public boolean containsFile(@NotNull String language) {
        return this.files.containsKey(language);
    }

    @Override
    public void addFile(@NotNull String language, @NotNull C configuration) {
        if (!containsFile(language)) {
            this.files.put(language, configuration);
        }
    }

    @Override
    public void removeFile(@NotNull String language) {
        if (containsFile(language)) {
            this.files.remove(language);
        }
    }

    @Override
    public C getFile(@NotNull String language) {
        return this.files.get(language);
    }

    @Override
    public C getDefaultFile() {
        return this.defaultFile;
    }
}
