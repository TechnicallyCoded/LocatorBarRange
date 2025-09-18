package com.tcoded.locatorbarrange.listener;

import com.tcoded.locatorbarrange.config.Config;
import com.tcoded.locatorbarrange.util.AttributeUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoinListener implements Listener {

    private final Config config;
    private final Plugin plugin;

    public PlayerJoinListener(Config config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        AttributeUtility.updatePlayer(event.getPlayer(), config, plugin);
    }

}

