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
    public String set(Object holder, String text) {
        if (holder == null) {
            return text;
        }

        if (!(holder instanceof Player)) {
            return text;
        }

        Player player = (Player) holder;

        if (!plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI").isEnabled()) {
            return text;
        }

        return PlaceholderAPI.setPlaceholders(player, text);
    }
}
