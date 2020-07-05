package dev.notcacha.languagelib.bukkit.message;

import dev.notcacha.languagelib.bukkit.BukkitLanguageLib;
import dev.notcacha.languagelib.message.TranslateMessage;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LanguageTranslateMessage implements TranslateMessage {

    private final String path;
    private final BukkitLanguageLib<YamlConfiguration> bukkitLanguageLib;
    private final Map<String, String> variables;

    public LanguageTranslateMessage(@NotNull String path, @NotNull BukkitLanguageLib<YamlConfiguration> bukkitLanguageLib) {
        this.path = path;
        this.bukkitLanguageLib = bukkitLanguageLib;
        this.variables = new ConcurrentHashMap<>();
    }

    @Override
    public @NotNull String getPath() {
        return this.path;
    }

    @Override
    public @NotNull String getMessage(@NotNull String language) {
        String messageTranslate;

        if (this.bukkitLanguageLib.getTranslateManager().containsFile(language)) {
            messageTranslate = this.bukkitLanguageLib.getFile(language).getString(getPath());
        } else {
            messageTranslate = this.bukkitLanguageLib.getDefaultFile().getString(getPath());
        }
        for (String key : variables.keySet()) {
            String value = variables.get(key);
            messageTranslate = messageTranslate.replace(key, value);
        }
        return messageTranslate;
    }

    @Override
    public @NotNull TranslateMessage setVariable(@NotNull String key, @NotNull String value) {
        this.variables.put(key, value);
        return this;
    }
}
