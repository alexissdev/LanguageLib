package dev.notcacha.languagelib.bukkit.message;

import dev.notcacha.languagelib.managers.FileManageable;
import dev.notcacha.languagelib.message.TranslatableMessage;
import dev.notcacha.languagelib.placeholder.PlaceholderApplier;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BukkitTranslatableMessage implements TranslatableMessage {

    private Object holder;

    private final String path;
    private final FileManageable fileManageable;
    private final Map<String, String> variables;
    private boolean color;

    private Set<PlaceholderApplier> placeholdersApplier;

    public BukkitTranslatableMessage(String path, FileManageable fileManageable) {
        this.path = path;
        this.fileManageable = fileManageable;
        this.variables = new ConcurrentHashMap<>();
        this.color = false;
        this.placeholdersApplier = new HashSet<>();
        this.holder = null;
    }

    @Override
    @NotNull
    public String getPath() {
        return this.path;
    }

    @Override
    public String getMessage(String language) {
        String translateMessage;

        if (fileManageable.exists(language)) {
            translateMessage = fileManageable.find(language).getString(getPath());
        } else {
            translateMessage = fileManageable.getDefault().getString(getPath());
        }

        for (String key : variables.keySet()) {
            String value = variables.get(key);
            translateMessage = translateMessage.replace(key, value);
        }

        for (PlaceholderApplier placeholderApplier : placeholdersApplier) {
            translateMessage = placeholderApplier.apply(holder, translateMessage);
        }

        if (color) {
            return ChatColor.translateAlternateColorCodes('&', translateMessage);
        }

        return translateMessage;
    }

    @Override
    public List<String> getMessages(String language) {
        List<String> translateMessages;

        if (fileManageable.exists(language)) {
            translateMessages = fileManageable.find(language).getList(getPath());
        } else {
            translateMessages = fileManageable.getDefault().getList(getPath());
        }

        for (String key : variables.keySet()) {
            String value = variables.get(key);
            translateMessages.replaceAll(message -> message.replace(key, value));
        }

        for (PlaceholderApplier placeholderApplier : placeholdersApplier) {
            translateMessages.replaceAll(message -> placeholderApplier.apply(holder, message));
        }

        if (color) {
            translateMessages.replaceAll(message -> ChatColor.translateAlternateColorCodes('&', message));
        }

        return translateMessages;
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
    public TranslatableMessage setHolder(Object holder) {
        this.holder = holder;
        return this;
    }

    @Override
    public TranslatableMessage addPlaceholder(PlaceholderApplier placeholder) {
        this.placeholdersApplier.add(placeholder);
        return this;
    }

    @Override
    public TranslatableMessage setPlaceholders(PlaceholderApplier... placeholders) {
        this.placeholdersApplier = new HashSet<>(Arrays.asList(placeholders));
        return this;
    }

    @Override
    public TranslatableMessage colorize() {
        this.color = true;
        return this;
    }
}
