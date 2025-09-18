package com.tcoded.locatorbarrange.config;

import org.bukkit.configuration.ConfigurationSection;

public record WorldRangeConfig(double viewRange, double sendRange) {

    public static WorldRangeConfig deserialize(ConfigurationSection section) {

        double viewRange = section.getDouble("view-range");
        double sendRange = section.getDouble("send-range");

        return new WorldRangeConfig(viewRange, sendRange);
    }
}
