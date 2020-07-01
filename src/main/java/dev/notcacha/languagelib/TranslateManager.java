package dev.notcacha.languagelib;

import dev.notcacha.languagelib.message.TranslateMessage;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface TranslateManager {

    Optional<TranslateMessage> getTranslate(@NotNull String path);

    boolean containsFile(@NotNull String language);

    void addFile(@NotNull String language, @NotNull YamlConfiguration configuration);

    void removeFile(@NotNull String language);

}
