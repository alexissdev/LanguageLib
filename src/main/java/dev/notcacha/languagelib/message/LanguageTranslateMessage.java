package dev.notcacha.languagelib.message;

import dev.notcacha.languagelib.LanguageLib;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LanguageTranslateMessage implements TranslateMessage {
    
    private final LanguageLib languageLib;
    private final Map<String, String> variables;

    public LanguageTranslateMessage(@NotNull LanguageLib languageLib) {
        this.languageLib = languageLib;
        this.variables = new ConcurrentHashMap<>();
    }

    public @NotNull String getMessage(@NotNull String path, @NotNull String language) {
        String messageTranslate = "";

        if (this.languageLib.getTranslateManager().containsFile(language)) {
            messageTranslate = this.languageLib.getFile(language).getString(path);
        }

        return messageTranslate;
    }

    public @NotNull TranslateMessage setVariable(@NotNull String key, @NotNull String value) {
        variables.put(key, value);
        return this;
    }
}
