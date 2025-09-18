package com.tcoded.locatorbarrange.config;

import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public record PlayerOverridesConfig(Map<String, PlayerOverrideGroupConfig> overrides) {

    public static PlayerOverridesConfig deserialize(ConfigurationSection section) {

        Map<String, PlayerOverrideGroupConfig> overrides = new LinkedHashMap<>();

        for (String key : section.getKeys(false)) {
            ConfigurationSection subSection = section.getConfigurationSection(key);
            if (subSection == null) continue;

            overrides.put(key, PlayerOverrideGroupConfig.deserialize(subSection));
        }

        Map<String, PlayerOverrideGroupConfig> sortedOverrides = new LinkedHashMap<>();
        overrides.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().priority(), e1.getValue().priority()))
                .forEachOrdered(e -> sortedOverrides.put(e.getKey(), e.getValue()));

        return new PlayerOverridesConfig(sortedOverrides);
    }
}
