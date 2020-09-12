package dev.notcacha.languagelib.bungee.managers;

import dev.notcacha.languagelib.bungee.message.BungeeTranslatableMessage;
import dev.notcacha.languagelib.managers.FileManageable;
import dev.notcacha.languagelib.managers.TranslationManager;
import dev.notcacha.languagelib.message.TranslatableMessage;

public class BungeeTranslationManager implements TranslationManager {

    private final FileManageable fileManageable;

    public BungeeTranslationManager(FileManageable fileManageable) {
        this.fileManageable = fileManageable;
    }

    @Override
    public TranslatableMessage getTranslation(String path) {
        return new BungeeTranslatableMessage(path, fileManageable);
    }
}
