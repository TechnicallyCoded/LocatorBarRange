package com.tcoded.locatorbarrange.config;

import org.bukkit.configuration.ConfigurationSection;

public record Config(WorldSettingsConfig worldSettings, PlayerOverridesConfig playerOverrides) {

    public static Config deserialize(ConfigurationSection section) {

        WorldSettingsConfig worldSettings = WorldSettingsConfig.deserialize(section.getConfigurationSection("world-settings"));
        PlayerOverridesConfig playerOverrides = PlayerOverridesConfig.deserialize(section.getConfigurationSection("player-overrides"));

        return new Config(worldSettings, playerOverrides);
    }
}
