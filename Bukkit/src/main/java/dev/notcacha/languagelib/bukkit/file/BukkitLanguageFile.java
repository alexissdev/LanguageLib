package dev.notcacha.languagelib.bukkit.file;

import dev.notcacha.languagelib.file.LanguageFile;
import dev.notcacha.languagelib.i18n.I18n;
import dev.notcacha.languagelib.i18n.message.Message;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Collections;
import java.util.List;

public class BukkitLanguageFile implements LanguageFile {

    private final I18n i18n;
    private final YamlConfiguration file;

    public BukkitLanguageFile(I18n i18n, YamlConfiguration file) {
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
        if (file.getStringList(path) == null) {
            return Collections.singletonList(i18n.getMessage(Message.LIST_MESSAGE_NOT_FOUND.getId())
                    .replace("%path%", path));
        }

        return file.getStringList(path);
    }
}
