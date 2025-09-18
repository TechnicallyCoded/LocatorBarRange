package com.tcoded.locatorbarrange.config;

import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public record WorldSettingsConfig(Map<String, WorldRangeConfig> worldConfigs) {

    public static WorldSettingsConfig deserialize(ConfigurationSection section) {

        Map<String, WorldRangeConfig> worldConfigs = new HashMap<>();

        for (String key : section.getKeys(false)) {
            ConfigurationSection subSection = section.getConfigurationSection(key);
            if (subSection == null) continue;

            worldConfigs.put(key, WorldRangeConfig.deserialize(subSection));
        }

        return new WorldSettingsConfig(worldConfigs);
    }
}
