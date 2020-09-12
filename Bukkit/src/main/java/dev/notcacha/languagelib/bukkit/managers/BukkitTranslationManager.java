package dev.notcacha.languagelib.bukkit.managers;

import dev.notcacha.languagelib.bukkit.message.BukkitTranslatableMessage;
import dev.notcacha.languagelib.managers.FileManageable;
import dev.notcacha.languagelib.managers.TranslationManager;
import dev.notcacha.languagelib.message.TranslatableMessage;

public class BukkitTranslationManager implements TranslationManager {

    private final FileManageable fileManageable;

    public BukkitTranslationManager(FileManageable fileManageable) {
        this.fileManageable = fileManageable;
    }

    @Override
    public TranslatableMessage getTranslation(String path) {
        return new BukkitTranslatableMessage(path, fileManageable);
    }
}
