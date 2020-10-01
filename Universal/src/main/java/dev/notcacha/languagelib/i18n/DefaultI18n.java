package dev.notcacha.languagelib.i18n;

import dev.notcacha.languagelib.i18n.message.Message;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class DefaultI18n implements I18n {

    private final Map<String, String> messageMap;

    public DefaultI18n() {
        this.messageMap = new HashMap<>();

        messageMap.put(Message.MESSAGE_NOT_FOUND.getId(), "Message not found, on path %path%");
        messageMap.put(Message.LIST_MESSAGE_NOT_FOUND.getId(), "Message list not found, on path %path%");
        messageMap.put(Message.FILE_NOT_FOUND.getId(), "The %file_name% file was not found");
        messageMap.put(Message.FILE_LOAD_ERROR.getId(), "An error occurred while trying to load the %file_name% file");
    }

    @Override
    public String getMessage(String id) {
        return this.messageMap.get(id);
    }

    public void setMessage(@NotNull String key, @NotNull String message) {
        messageMap.put(key, message);
    }

}
