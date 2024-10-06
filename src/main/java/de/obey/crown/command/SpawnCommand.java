/* CrownPlugins - CrownSpawn */
/* 06.10.2024 - 05:18 */

package de.obey.crown.command;

import de.obey.crown.core.util.Teleporter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor @NonNull
public final class SpawnCommand implements CommandExecutor {

    private final String hi = "https://dsc.gg/crownplugins";
    private final String how = "https://dsc.gg/crownplugins";
    private final String are = "https://dsc.gg/crownplugins";
    private final String you = "https://dsc.gg/crownplugins";
    private final String doing = "https://dsc.gg/crownplugins";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player player))
            return false;

        Teleporter.teleportWithAnimation(player, "spawn");

        return false;
    }
}
