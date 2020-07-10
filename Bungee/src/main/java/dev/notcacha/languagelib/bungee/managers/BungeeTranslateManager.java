package dev.notcacha.languagelib.bungee.managers;

import dev.notcacha.languagelib.bungee.BungeeLanguageLib;
import dev.notcacha.languagelib.managers.TranslationManager;
import dev.notcacha.languagelib.bungee.message.BungeeTranslatableMessage;
import dev.notcacha.languagelib.message.TranslatableMessage;
import net.md_5.bungee.config.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BungeeTranslateManager implements TranslationManager {

    private final BungeeLanguageLib<Configuration> languageLib;

    public BungeeTranslateManager(BungeeLanguageLib languageLib) {
        this.languageLib = languageLib;
    }

    @Override
    public Optional<TranslatableMessage> getTranslation(@NotNull String path) {
        return Optional.ofNullable(languageLib.getFileManager().getDefaultFile().getString(path,null)).map(string -> new BungeeTranslatableMessage(path,languageLib));
    }
}
