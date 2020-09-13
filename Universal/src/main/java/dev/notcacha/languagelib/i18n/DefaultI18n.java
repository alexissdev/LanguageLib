package dev.notcacha.languagelib.i18n;

import dev.notcacha.languagelib.i18n.message.Message;

import java.util.HashMap;
import java.util.Map;

public class DefaultI18n implements I18n {

    private final Map<Message, String> messageMap;

    public DefaultI18n() {
        this.messageMap = new HashMap<>();

        messageMap.put(Message.MESSAGE_NOT_FOUND, "Message not found, on path %path%");
        messageMap.put(Message.LIST_MESSAGE_NOT_FOUND, "Message list not found, on path %path%");
        messageMap.put(Message.FILE_NOT_FOUND, "The %file_name% file was not found");
        messageMap.put(Message.FILE_LOAD_ERROR, "An error occurred while trying to load the %file_name% file");
    }

    @Override
    public String getMessage(Message message) {
        return this.messageMap.get(message);
    }

    public void setMessage(Message key, String value) {
        this.messageMap.put(key, value);
    }
}
