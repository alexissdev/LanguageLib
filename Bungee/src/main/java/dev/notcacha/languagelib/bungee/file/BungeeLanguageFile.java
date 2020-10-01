package dev.notcacha.languagelib.bungee.file;

import dev.notcacha.languagelib.file.LanguageFile;
import dev.notcacha.languagelib.i18n.I18n;
import dev.notcacha.languagelib.i18n.message.Message;
import net.md_5.bungee.config.Configuration;

import java.util.Collections;
import java.util.List;

public class BungeeLanguageFile implements LanguageFile {

    private final I18n i18n;
    private final Configuration file;

    public BungeeLanguageFile(I18n i18n, Configuration file) {
        this.i18n = i18n;
        this.file = file;
    }

    @Override
    public String getString(String path) {
        return file.getString(path,
                i18n.getMessage(Message.MESSAGE_NOT_FOUND.getId()).replace("%path%", path));
    }

    @Override
    public List<String> getList(String path) {
        if (file.getList(path) == null) {
            return Collections.singletonList(i18n.getMessage(Message.LIST_MESSAGE_NOT_FOUND.getId())
                    .replace("%path%", path));
        }

        return file.getStringList(path);
    }
}
