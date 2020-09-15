package dev.notcacha.languagelib.bukkit.placeholder;

import dev.notcacha.languagelib.placeholder.PlaceholderApplier;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PAPIPlaceholderApplier implements PlaceholderApplier {

    @Override
    public String apply(Object holder, String text) {
        if (!(holder instanceof Player)) {
            return text;
        }

        if (!Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI").isEnabled()) {
            return text;
        }

        Player player = (Player) holder;

        return PlaceholderAPI.setPlaceholders(player, text);
    }
}
