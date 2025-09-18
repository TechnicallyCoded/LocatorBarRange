package com.tcoded.locatorbarrange.util;

import com.tcoded.locatorbarrange.LocatorBarRange;
import com.tcoded.locatorbarrange.config.Config;
import com.tcoded.locatorbarrange.config.PlayerOverrideGroupConfig;
import com.tcoded.locatorbarrange.config.WorldRangeConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class AttributeUtility {

    public static NamespacedKey VIEW_DISTANCE_MODIFIER_KEY;
    public static NamespacedKey SEND_DISTANCE_MODIFIER_KEY;

    public static void setModifierKeys(NamespacedKey viewKey, NamespacedKey sendKey) {
        VIEW_DISTANCE_MODIFIER_KEY = viewKey;
        SEND_DISTANCE_MODIFIER_KEY = sendKey;
    }

    public static void updatePlayer(Player player, Config config, Plugin plugin) {
        WorldRangeConfig rangeConfig = getWorldRangeConfig(player, config);

        if (rangeConfig == null) {
            String worldName = player.getWorld().getName();
            plugin.getLogger().warning("No range config found for world " + worldName + " and no default config set, skipping player " + player.getName());
            return;
        }

        double targetView = rangeConfig.viewRange();
        double targetSend = rangeConfig.sendRange();

        AttributeInstance viewAttribute = player.getAttribute(Attribute.WAYPOINT_RECEIVE_RANGE);
        AttributeInstance sendAttribute = player.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE);

        double currView = viewAttribute.getValue();
        double currSend = sendAttribute.getValue();

        double viewDelta = targetView - currView;
        double sendDelta = targetSend - currSend;

        if (targetView >= 0) {
            AttributeModifier viewModifier = new AttributeModifier(VIEW_DISTANCE_MODIFIER_KEY, viewDelta, AttributeModifier.Operation.ADD_NUMBER);
            viewAttribute.addTransientModifier(viewModifier);
        }

        if (targetSend >= 0) {
            AttributeModifier sendModifier = new AttributeModifier(SEND_DISTANCE_MODIFIER_KEY, sendDelta, AttributeModifier.Operation.ADD_NUMBER);
            sendAttribute.addTransientModifier(sendModifier);
        }

        plugin.getLogger().info("Updated player " + player.getName() + ": view " + currView + " -> " + targetView + ", send " + currSend + " -> " + targetSend);
    }

    public static WorldRangeConfig getWorldRangeConfig(Player player, Config config) {
        Map<String, WorldRangeConfig> worldConfigs = config.worldSettings().worldConfigs();
        PlayerOverrideGroupConfig playerOverride = getOverrideForPlayer(player, config);
        if (playerOverride != null) {
            worldConfigs = playerOverride.worldSettings().worldConfigs();
        }

        WorldRangeConfig rangeConfig = worldConfigs.get("_default_");
        WorldRangeConfig worldOverride = worldConfigs.get(player.getWorld().getName());
        if (worldOverride != null) rangeConfig = worldOverride;

        return rangeConfig;
    }

    public static PlayerOverrideGroupConfig getOverrideForPlayer(Player player, Config config) {
        for (PlayerOverrideGroupConfig override : config.playerOverrides().overrides().values()) {
            if (!player.hasPermission(override.permission())) continue;
            return override;
        }
        return null;
    }

    public static void removePlayer(Player player, LocatorBarRange plugin) {
        AttributeInstance viewAttribute = player.getAttribute(Attribute.WAYPOINT_RECEIVE_RANGE);
        AttributeInstance sendAttribute = player.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE);

        if (viewAttribute != null) viewAttribute.removeModifier(VIEW_DISTANCE_MODIFIER_KEY);
        if (sendAttribute != null) sendAttribute.removeModifier(SEND_DISTANCE_MODIFIER_KEY);

        plugin.getLogger().info("Removed modifiers for player " + player.getName());
    }

}
