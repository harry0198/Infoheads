package me.harry0198.infoheads.utils;

import org.bukkit.Bukkit;

public final class Settings {

    private boolean offhand = true;
    private boolean papi = false;

    public Settings() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            papi = true;

        if (Bukkit.getServer().getVersion().contains("1.8"))
            offhand = false;
    }

    public boolean isOffhand() {
        return offhand;
    }

    public boolean isPapiEnabled() {
        return papi;
    }
}
