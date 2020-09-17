package dev.notcacha.languagelib.managers;

import dev.notcacha.languagelib.message.SimpleTranslatableMessage;
import dev.notcacha.languagelib.message.TranslatableMessage;

public class SimpleTranslationManager implements TranslationManager {

    private final FileManageable fileManageable;

    public SimpleTranslationManager(FileManageable fileManageable) {
        this.fileManageable = fileManageable;
    }

    @Override
    public TranslatableMessage getTranslation(String path) {
        return new SimpleTranslatableMessage(path, fileManageable);
    }
}
