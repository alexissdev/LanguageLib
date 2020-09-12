package dev.notcacha.languagelib.i18n;

import dev.notcacha.languagelib.i18n.message.Message;

public interface I18n {

    /**
     * @return message from I18n, {@param message} key from message has been get
     */

    String getMessage(Message message);
}
