package dev.notcacha.languagelib.message;

import dev.notcacha.languagelib.managers.FileManageable;
import dev.notcacha.languagelib.placeholder.PlaceholderApplier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleTranslatableMessage implements TranslatableMessage {

    private final String path;
    private final FileManageable fileManageable;
    private final Map<String, String> variables;
    private final Map<PlaceholderApplier, Object> appliers;
    private boolean color;

    public SimpleTranslatableMessage(String path, FileManageable fileManageable) {
        this.path = path;
        this.fileManageable = fileManageable;
        this.variables = new ConcurrentHashMap<>();
        this.appliers = new ConcurrentHashMap<>();
        this.color = false;
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

        for (PlaceholderApplier placeholderApplier : this.appliers.keySet()) {
            translateMessage = placeholderApplier.apply(this.appliers.get(placeholderApplier), translateMessage);
        }

        if (color) {
            return MessageColorApplier.apply('&', translateMessage);
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

        for (PlaceholderApplier placeholderApplier : this.appliers.keySet()) {
            translateMessages.replaceAll(message -> placeholderApplier.apply(this.appliers.get(placeholderApplier), message));
        }

        if (color) {
            translateMessages.replaceAll(message -> MessageColorApplier.apply('&', message));
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
    public <T> TranslatableMessage addPlaceholder(T holder, PlaceholderApplier placeholder) {
        this.appliers.put(placeholder, holder);

        return this;
    }

    @Override
    public TranslatableMessage colorize() {
        this.color = true;
        return this;
    }

}

class MessageColorApplier {

    private static final char COLOR_CHAR = '\u00A7';
    private static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";

    public static String apply(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();

        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && ALL_CODES.indexOf(b[i + 1]) > -1) {
                b[i] = COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

}
