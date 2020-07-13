package dev.notcacha.languagelib.bungee.message;

import dev.notcacha.languagelib.bungee.BungeeLanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BungeeTranslatableMessage implements TranslatableMessage {

    private final String path;
    private final BungeeLanguageLib bungeeLanguageLib;
    private final Map<String, String> variables;
    private boolean color;

    public BungeeTranslatableMessage(@NotNull String path, @NotNull BungeeLanguageLib bungeeLanguageLib) {
        this.path = path;
        this.bungeeLanguageLib = bungeeLanguageLib;
        this.variables = new ConcurrentHashMap<>();
        this.color = false;
    }

    @Override
    public @NotNull String getPath() {
        return this.path;
    }

    @Override
    public @NotNull String getMessage(String language) {
        String messageTranslate;

        if (language != null && this.bungeeLanguageLib.getFileManager().containsFile(language)) {
            messageTranslate = this.bungeeLanguageLib.getFileManager().getFile(language).getString(getPath());
        } else {
            messageTranslate = this.bungeeLanguageLib.getFileManager().getDefaultFile().getString(getPath());
        }
        for (String key : variables.keySet()) {
            String value = variables.get(key);
            messageTranslate = messageTranslate.replace(key, value);
        }
        if (color) {
            return ChatColor.translateAlternateColorCodes('&', messageTranslate);
        }

        return messageTranslate;
    }

    @NotNull
    @Override
    public List<String> getMessages(String language) {
        List<String> messageTranslate;

        if (language != null && this.bungeeLanguageLib.getFileManager().containsFile(language)) {
            messageTranslate = this.bungeeLanguageLib.getFileManager().getFile(language).getStringList(getPath());
        } else {
            messageTranslate = this.bungeeLanguageLib.getFileManager().getDefaultFile().getStringList(getPath());
        }
        for (String key : variables.keySet()) {
            String value = variables.get(key);
            messageTranslate.replaceAll(message -> message.replace(key, value));
        }
        if (color) {
            messageTranslate.replaceAll(message -> message.replace(message, ChatColor.translateAlternateColorCodes('&', message)));
        }
        return messageTranslate;
    }

    @Override
    public @NotNull TranslatableMessage setVariable(@NotNull String key, @Nullable String value) {
        if (value == null) {
            this.variables.remove(key);
        } else {
            this.variables.put(key, value);
        }
        return this;
    }

    @NotNull
    @Override
    public TranslatableMessage setColor(boolean setColor) {
        this.color = setColor;
        return this;
    }

}
