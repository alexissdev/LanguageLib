package dev.notcacha.languagelib.bukkit.message;

import dev.notcacha.languagelib.bukkit.BukkitLanguageLib;
import dev.notcacha.languagelib.message.TranslatableMessage;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BukkitTranslatableMessage implements TranslatableMessage {

    private final String path;
    private final BukkitLanguageLib bukkitLanguageLib;
    private final Map<String, String> variables;
    private boolean color;

    public BukkitTranslatableMessage(String path, BukkitLanguageLib bukkitLanguageLib) {
        this.path = path;
        this.bukkitLanguageLib = bukkitLanguageLib;
        this.variables = new ConcurrentHashMap<>();
        this.color = false;
    }

    @NotNull
    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getMessage(String language) {
        String messageTranslate;

        if (language != null && this.bukkitLanguageLib.getFileManager().containsFile(language)) {
            messageTranslate = this.bukkitLanguageLib.getFileManager().getFile(language).getString(getPath());
        } else {
            messageTranslate = this.bukkitLanguageLib.getFileManager().getDefaultFile().getString(getPath());
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

    @Override
    public List<String> getMessages(String language) {
        List<String> messageTranslate;

        if (language != null && this.bukkitLanguageLib.getFileManager().containsFile(language)) {
            messageTranslate = this.bukkitLanguageLib.getFileManager().getFile(language).getStringList(getPath());
        } else {
            messageTranslate = this.bukkitLanguageLib.getFileManager().getDefaultFile().getStringList(getPath());
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
    public TranslatableMessage setVariable(String key, String value) {
        if (value == null) {
            this.variables.remove(key);
        } else {
            this.variables.put(key, value);
        }
        return this;
    }

    @Override
    public TranslatableMessage setColor(boolean setColor) {
        this.color = setColor;
        return this;
    }
}
