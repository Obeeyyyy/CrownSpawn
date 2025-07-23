/* CrownPlugins - CrownSpawn */
/* 07.10.2024 - 04:20 */

package de.obey.crown.listener;

import de.obey.crown.core.handler.LocationHandler;
import de.obey.crown.core.util.Scheduler;
import de.obey.crown.core.util.Teleporter;
import de.obey.crown.noobf.CrownSpawn;
import de.obey.crown.noobf.PluginConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

@RequiredArgsConstructor
public final class PlayerJoin implements Listener {

    private final PluginConfig pluginConfig;

    @EventHandler
    public void on(final PlayerSpawnLocationEvent event) {
        if(!event.getPlayer().hasPlayedBefore()) {
            if (pluginConfig.isTeleportToSpawnOnFirstJoin()) {
                final Location spawn = LocationHandler.getLocation("spawn");

                if(spawn == null)
                    return;

                event.setSpawnLocation(spawn);


                Scheduler.runTaskLater(CrownSpawn.getInstance(), () -> {
                    Teleporter.teleportInstant(event.getPlayer(), spawn);
                },2);
                return;
            }
        }


        if (pluginConfig.isTeleportToSpawnOnJoin()) {
            final Location spawn = LocationHandler.getLocation("spawn");

            if(spawn == null)
                return;

            event.setSpawnLocation(spawn);
        }
    }

}
