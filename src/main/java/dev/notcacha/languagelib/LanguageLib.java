package dev.notcacha.languagelib;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class LanguageLib {

    private final Map<String, YamlConfiguration> files;
    @NotNull
    private final TranslateManager translateManager;

    public LanguageLib() {
        this.files = new HashMap<>();
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
}
