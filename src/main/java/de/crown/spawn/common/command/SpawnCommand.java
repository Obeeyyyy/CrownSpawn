/* CrownPlugins - CrownSpawn */
/* 06.10.2024 - 05:18 */

package de.crown.spawn.common.command;

import de.obey.crown.core.data.plugin.Messanger;
import de.obey.crown.core.util.Teleporter;
import de.crown.spawn.common.CrownSpawn;
import de.crown.spawn.common.PluginConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public final class SpawnCommand implements CommandExecutor, TabCompleter {

    private final PluginConfig pluginConfig;
    private final Messanger messanger;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {
        if(!(sender instanceof Player player))
            return false;

        if(args.length == 1) {
            if(!messanger.hasPermission(sender, "command.spawn.admin"))
                return false;

            if(args[0].equalsIgnoreCase("reload")) {
                pluginConfig.loadConfig();
                pluginConfig.loadMessages();
                pluginConfig.loadSounds();

                messanger.sendMessage(sender, "plugin-reloaded", new String[]{"plugin"}, CrownSpawn.getInstance().getName());
                return false;
            }
        }

        Teleporter.teleportWithAnimation(player, "spawn");

        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {
        final ArrayList<String> list = new ArrayList<>();

        if(!(sender instanceof Player))
            return list;

        if(!messanger.hasPermission(sender, "command.spawn.admin", false))
            return list;

        if(args.length == 1)
            list.add("reload");

        final String argument = args[args.length - 1];
        if (!argument.isEmpty())
            list.removeIf(value -> !value.toLowerCase().startsWith(argument.toLowerCase()));

        Collections.sort(list);

        return list;
    }
}
