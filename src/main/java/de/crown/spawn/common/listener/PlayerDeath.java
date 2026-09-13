/* CrownPlugins - CrownCore */
/* 21.08.2024 - 02:38 */

package de.crown.spawn.common.listener;

import de.crown.spawn.common.PluginConfig;
import de.obey.crown.core.handler.LocationHandler;
import de.obey.crown.core.noobf.CrownCore;
import de.obey.crown.core.util.Scheduler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

@RequiredArgsConstructor
public final class PlayerDeath implements Listener {

    private final PluginConfig pluginConfig;

    @EventHandler
    public void on(final PlayerDeathEvent event) {

        if (pluginConfig.isInstantRespawn())
            Scheduler.runEntityTaskLater(CrownCore.getInstance(), event.getPlayer(), () -> event.getEntity().spigot().respawn(), 2);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(final PlayerRespawnEvent event) {
        if(!pluginConfig.isTeleportToSpawnOnRespawn())
            return;

        if (pluginConfig.isSpawnAtBed()) {
            if (event.isBedSpawn() || event.isAnchorSpawn())
                return;

            final Location bedLocation = getBedLocation(event.getPlayer());
            if (bedLocation != null && !event.isMissingRespawnBlock()) {
                event.setRespawnLocation(bedLocation);
                return;
            }
        }

        final Location spawn = LocationHandler.getLocation("spawn");

        if (spawn == null)
            return;

        event.setRespawnLocation(spawn);
    }

    private Location getBedLocation(final Player player) {
        if (player == null)
            return null;

        final Location respawnLocation = player.getRespawnLocation();
        if (respawnLocation != null)
            return respawnLocation;

        return player.getBedSpawnLocation();
    }
}
