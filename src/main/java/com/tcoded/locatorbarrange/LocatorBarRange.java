package com.tcoded.locatorbarrange;

import com.tcoded.locatorbarrange.command.LocatorBarRangeCommand;
import com.tcoded.locatorbarrange.config.Config;
import com.tcoded.locatorbarrange.listener.PlayerJoinListener;
import org.bukkit.NamespacedKey;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.tcoded.locatorbarrange.util.AttributeUtility;

public final class LocatorBarRange extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {
        NamespacedKey viewKey = NamespacedKey.fromString("view-distance", this);
        NamespacedKey sendKey = NamespacedKey.fromString("send-distance", this);
        AttributeUtility.setModifierKeys(viewKey, sendKey);

        this.reloadConfig();

        this.getCommand("locatorbarrange").setExecutor(new LocatorBarRangeCommand(this));
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(config, this), this);

        getServer().getOnlinePlayers().forEach(player -> AttributeUtility.updatePlayer(player, config, this));
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        saveDefaultConfig();
        this.config = Config.deserialize(getConfig());
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getServer().getOnlinePlayers().forEach(player -> AttributeUtility.removePlayer(player, this));
    }

    public Config getConfiguration() {
        return this.config;
    }

}
