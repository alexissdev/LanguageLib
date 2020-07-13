package dev.notcacha.languagelib.bukkit.managers;

import dev.notcacha.languagelib.bukkit.BukkitLanguageLib;
import dev.notcacha.languagelib.managers.TranslationManager;
import dev.notcacha.languagelib.bukkit.message.BukkitTranslatableMessage;
import dev.notcacha.languagelib.message.TranslatableMessage;

import java.util.Optional;

public class BukkitTranslateManager implements TranslationManager {

    private final BukkitLanguageLib bukkitLanguageLib;

    public BukkitTranslateManager(BukkitLanguageLib bukkitLanguageLib) {
        this.bukkitLanguageLib = bukkitLanguageLib;
    }

    @Override
    public Optional<TranslatableMessage> getTranslation(String path) {
        return Optional.ofNullable(bukkitLanguageLib.getFileManager().getDefaultFile().getString(path,null)).map(string -> new BukkitTranslatableMessage(path, bukkitLanguageLib));
    }

}