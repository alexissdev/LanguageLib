package dev.notcacha.languagelib;

import dev.notcacha.languagelib.message.LanguageTranslateMessage;
import dev.notcacha.languagelib.message.TranslateMessage;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class TranslateManagerImplementation implements TranslateManager {

    @NotNull
    private final LanguageLib languageLib;

    public TranslateManagerImplementation(@NotNull LanguageLib languageLib) {
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
    public void addFile(@NotNull String language, @NotNull YamlConfiguration configuration) {
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
