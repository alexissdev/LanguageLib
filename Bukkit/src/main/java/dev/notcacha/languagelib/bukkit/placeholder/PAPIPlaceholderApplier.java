package dev.notcacha.languagelib.bukkit.placeholder;

import dev.notcacha.languagelib.placeholder.PlaceholderApplier;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PAPIPlaceholderApplier implements PlaceholderApplier {

    private final Plugin plugin;

    public PAPIPlaceholderApplier(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public <T> String apply(T holder, String text) {
        if (!(holder instanceof Player)) {
            return text;
        }

        if (plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            return text;
        }

        return PlaceholderAPI.setPlaceholders((Player) holder, text);
    }
}
