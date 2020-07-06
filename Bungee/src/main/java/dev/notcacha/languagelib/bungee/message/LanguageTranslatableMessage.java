package dev.notcacha.languagelib.bungee.message;

import dev.notcacha.languagelib.bungee.BungeeLanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import net.md_5.bungee.config.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LanguageTranslatableMessage implements TranslatableMessage {

    private final String path;
    private final BungeeLanguageLib<Configuration> bungeeLanguageLib;
    private final Map<String, String> variables;

    public LanguageTranslatableMessage(@NotNull String path, @NotNull BungeeLanguageLib<Configuration> bungeeLanguageLib) {
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

        try {
            return messageTranslate;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Path '" + getPath() + "' not found please check path");
        }
    }

    @NotNull
    @Override
    public List<String> getMessages(@NotNull String language) {
        List<String> messageTranslate;

        if (this.bungeeLanguageLib.getTranslateManager().containsFile(language)) {
            messageTranslate = this.bungeeLanguageLib.getFile(language).getStringList(getPath());
        } else {
            messageTranslate = this.bungeeLanguageLib.getDefaultFile().getStringList(getPath());
        }

        for (String key : variables.keySet()) {
            String value = variables.get(key);
            messageTranslate.replaceAll(message -> message.replace(key, value));
        }

        try {
            return messageTranslate;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Path '" + getPath() + "' not found please check path");
        }
    }

    @Override
    public @NotNull TranslatableMessage setVariable(@NotNull String key, @NotNull String value) {
        this.variables.put(key, value);
        return this;
    }

    private boolean isList(Configuration file, String path) {
        Object val = file.get(path);
        return val instanceof List;
    }

}
