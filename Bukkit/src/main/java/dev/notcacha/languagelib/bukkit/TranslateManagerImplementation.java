package dev.notcacha.languagelib.bukkit;

import dev.notcacha.languagelib.TranslateManager;
import dev.notcacha.languagelib.bukkit.message.LanguageTranslatableMessage;
import dev.notcacha.languagelib.message.TranslatableMessage;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class TranslateManagerImplementation<C extends YamlConfiguration> implements TranslateManager<C> {

    @NotNull
    private final BukkitLanguageLib bukkitLanguageLib;

    public TranslateManagerImplementation(@NotNull BukkitLanguageLib bukkitLanguageLib) {
        this.bukkitLanguageLib = bukkitLanguageLib;
    }

    @Override
    public Optional<TranslatableMessage> getTranslate(@NotNull String path) {
        return Optional.ofNullable(bukkitLanguageLib.getDefaultFile().getString(path,null)).map(string -> new LanguageTranslatableMessage(path, bukkitLanguageLib));
    }

    @Override
    public boolean containsFile(@NotNull String language) {
        return this.bukkitLanguageLib.getFiles().containsKey(language);
    }

    @Override
    public void addFile(@NotNull String language, @NotNull C configuration) {
        if (!containsFile(language)) {
            this.bukkitLanguageLib.getFiles().put(language, configuration);
        }
    }

    @Override
    public void removeFile(@NotNull String language) {
        if (containsFile(language)) {
            this.bukkitLanguageLib.getFiles().remove(language);
        }
    }

}
