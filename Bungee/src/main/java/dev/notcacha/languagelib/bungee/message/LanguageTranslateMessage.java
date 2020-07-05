package dev.notcacha.languagelib.bungee.message;

import dev.notcacha.languagelib.bungee.BungeeLanguageLib;
import dev.notcacha.languagelib.message.TranslateMessage;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LanguageTranslateMessage implements TranslateMessage {

    private final String path;
    private final BungeeLanguageLib bungeeLanguageLib;
    private final Map<String, String> variables;

    public LanguageTranslateMessage(@NotNull String path, @NotNull BungeeLanguageLib bungeeLanguageLib) {
        this.path = path;
        this.bungeeLanguageLib = bungeeLanguageLib;
        this.variables = new ConcurrentHashMap<>();
    }

    @Override
    public @NotNull String getPath() {
        return this.path;
    }

    @Override
    public @NotNull String getMessage(@NotNull String language) {
        String messageTranslate;

        if (this.bungeeLanguageLib.getTranslateManager().containsFile(language)) {
            messageTranslate = this.bungeeLanguageLib.getFile(language).getString(getPath());
        } else {
            messageTranslate = this.bungeeLanguageLib.getDefaultFile().getString(getPath());
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
