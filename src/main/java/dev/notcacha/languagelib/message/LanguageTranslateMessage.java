package dev.notcacha.languagelib.message;

import dev.notcacha.languagelib.LanguageLib;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LanguageTranslateMessage implements TranslateMessage {

    private final String path;
    private final LanguageLib languageLib;
    private final Map<String, String> variables;

    public LanguageTranslateMessage(@NotNull String path, @NotNull LanguageLib languageLib) {
        this.path = path;
        this.languageLib = languageLib;
        this.variables = new ConcurrentHashMap<>();
    }

    @Override
    public @NotNull String getPath() {
        return this.path;
    }

    @Override
    public @NotNull String getMessage(@NotNull String language) {
        String messageTranslate;

        if (this.languageLib.getTranslateManager().containsFile(language)) {
            messageTranslate = this.languageLib.getFile(language).getString(getPath());
        } else {
            messageTranslate = this.languageLib.getDefaultFile().getString(getPath());
        }
        for (String key : variables.values()) {
            String value = variables.get(key);
            messageTranslate = messageTranslate.replace(key, value);
        }
        return messageTranslate;
    }

    @Override
    public @NotNull TranslateMessage setVariable(@NotNull String key, @NotNull String value) {
        variables.put(key, value);
        return this;
    }
}
