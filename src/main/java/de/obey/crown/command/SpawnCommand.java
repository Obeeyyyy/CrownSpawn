/* CrownPlugins - CrownSpawn */
/* 06.10.2024 - 05:18 */

package de.obey.crown.command;

import de.obey.crown.core.data.plugin.Messanger;
import de.obey.crown.core.util.Teleporter;
import de.obey.crown.noobf.CrownSpawn;
import de.obey.crown.noobf.PluginConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public final class SpawnCommand implements CommandExecutor, TabCompleter {

    private final String hi = "https://dsc.gg/crownplugins";
    private final String how = "https://dsc.gg/crownplugins";
    private final String are = "https://dsc.gg/crownplugins";
    private final String you = "https://dsc.gg/crownplugins";
    private final String doing = "https://dsc.gg/crownplugins";

    private final PluginConfig pluginConfig;
    private final Messanger messanger;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
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
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        final ArrayList<String> list = new ArrayList<>();

        if(!(sender instanceof Player player))
            return list;

        if(!messanger.hasPermission(sender, "command.spawn.admin", false))
            return list;

        if(args.length == 1) {
            list.add("reload");
        }

        final String argument = args[args.length - 1];
        if (!argument.isEmpty())
            list.removeIf(value -> !value.toLowerCase().startsWith(argument.toLowerCase()));

        Collections.sort(list);

        return list;
    }
}
