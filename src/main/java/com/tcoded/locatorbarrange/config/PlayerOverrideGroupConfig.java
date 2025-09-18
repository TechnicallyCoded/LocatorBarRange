package com.tcoded.locatorbarrange.config;

import org.bukkit.configuration.ConfigurationSection;

public record PlayerOverrideGroupConfig(int priority, String permission, WorldSettingsConfig worldSettings) {

    public static PlayerOverrideGroupConfig deserialize(ConfigurationSection section) {

        int priority = section.getInt("priority");
        String permission = section.getString("permission");
        WorldSettingsConfig worldSettings = WorldSettingsConfig.deserialize(section);

        return new PlayerOverrideGroupConfig(priority, permission, worldSettings);
    }
}
