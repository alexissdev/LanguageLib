package dev.notcacha.languagelib.bungee;

import dev.notcacha.languagelib.TranslateManager;
import dev.notcacha.languagelib.bungee.message.LanguageTranslateMessage;
import dev.notcacha.languagelib.message.TranslateMessage;
import net.md_5.bungee.config.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class TranslateManagerImplementation<C extends Configuration> implements TranslateManager<C> {

    @NotNull
    private final BungeeLanguageLib languageLib;

    public TranslateManagerImplementation(@NotNull BungeeLanguageLib languageLib) {
        this.languageLib = languageLib;
    }

    @Override
    public Optional<TranslateMessage> getTranslate(@NotNull String path) {
        return Optional.ofNullable(languageLib.getDefaultFile().getString(path,null)).map(string -> new LanguageTranslateMessage(path,languageLib));
    }

    @Override
    public boolean containsFile(@NotNull String language) {
        return this.languageLib.getFiles().containsKey(language);
    }

    @Override
    public void addFile(@NotNull String language, @NotNull C configuration) {
        if (!containsFile(language)) {
            this.languageLib.getFiles().put(language, configuration);
        }
    }

    @Override
    public void removeFile(@NotNull String language) {
        if (containsFile(language)) {
            this.languageLib.getFiles().remove(language);
        }
    }

}
