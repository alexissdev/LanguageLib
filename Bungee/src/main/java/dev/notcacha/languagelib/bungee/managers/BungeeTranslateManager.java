package dev.notcacha.languagelib.bungee.managers;

import dev.notcacha.languagelib.bungee.BungeeLanguageLib;
import dev.notcacha.languagelib.bungee.message.BungeeTranslatableMessage;
import dev.notcacha.languagelib.managers.TranslationManager;
import dev.notcacha.languagelib.message.TranslatableMessage;

import java.util.Optional;

public class BungeeTranslateManager implements TranslationManager {

    private final BungeeLanguageLib bungeeLanguageLib;

    public BungeeTranslateManager(BungeeLanguageLib bungeeLanguageLib) {
        this.bungeeLanguageLib = bungeeLanguageLib;
    }

    @Override
    public Optional<TranslatableMessage> getTranslation(String path) {
        return Optional.of(bungeeLanguageLib.getFileManager().getDefaultFile().contains(path)).map(string -> new BungeeTranslatableMessage(path, bungeeLanguageLib));
    }
}
