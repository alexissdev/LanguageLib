package dev.notcacha.languagelib;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class LanguageLib {

    private final YamlConfiguration defaultFile;
    private final Map<String, YamlConfiguration> files;
    @NotNull
    private final TranslateManager translateManager;

    public LanguageLib(@NotNull String defaultLanguage, @NotNull YamlConfiguration defaultFile) {
        this.files = new HashMap<>();
        this.defaultFile = defaultFile;
        this.files.put(defaultLanguage, defaultFile);
        this.translateManager = new TranslateManagerImplementation(this);
    }

    protected Map<String, YamlConfiguration> getFiles() {
        return this.files;
    }

    public YamlConfiguration getFile(@NotNull String language) {
        return this.files.get(language);
    }

    public @NotNull TranslateManager getTranslateManager() {
        return translateManager;
    }

    public YamlConfiguration getDefaultFile() {
        return defaultFile;
    }
}
