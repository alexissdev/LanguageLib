package dev.notcacha.languagelib.i18n.message;

public enum Message {

    MESSAGE_NOT_FOUND("message.simple.not_found"),
    LIST_MESSAGE_NOT_FOUND("message.list.not_found"),
    FILE_NOT_FOUND("file.not_found"),
    FILE_LOAD_ERROR("file.load_error");

    private final String id;

    Message(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }

}
