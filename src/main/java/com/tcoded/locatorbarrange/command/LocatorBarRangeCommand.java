package com.tcoded.locatorbarrange.command;

import com.tcoded.locatorbarrange.LocatorBarRange;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocatorBarRangeCommand implements TabExecutor {

    private final LocatorBarRange plugin;

    public LocatorBarRangeCommand(LocatorBarRange plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0 || !args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage("§cUsage: /locatorbarrange reload");
            return true;
        }

        plugin.reloadConfig();
        sender.sendMessage("§aLocatorBarRange configuration reloaded.");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> options = new ArrayList<>();

        if (args.length == 1) {
            options.add("reload");
        }

        String current = args[args.length - 1].toLowerCase();
        return options.stream()
                .filter(opt -> opt.toLowerCase().startsWith(current))
                .collect(Collectors.toList());
    }
}
