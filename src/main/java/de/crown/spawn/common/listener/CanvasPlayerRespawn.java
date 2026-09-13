/* CrownPlugins - CrownCore */
/* 21.08.2024 - 02:38 */

package de.crown.spawn.common.listener;

import de.crown.spawn.common.PluginConfig;
import de.obey.crown.core.handler.LocationHandler;
import io.canvasmc.canvas.event.PlayerRespawnAsyncEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public final class CanvasPlayerRespawn implements Listener {

    private final PluginConfig pluginConfig;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(final PlayerRespawnAsyncEvent event) {
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
