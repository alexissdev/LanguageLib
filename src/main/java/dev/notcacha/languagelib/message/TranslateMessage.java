package dev.notcacha.languagelib.message;

import org.jetbrains.annotations.NotNull;

public interface TranslateMessage {

    @NotNull
    String getMessage(@NotNull String path, @NotNull String language);

    @NotNull
    TranslateMessage setVariable(@NotNull String key, @NotNull String value);
}
