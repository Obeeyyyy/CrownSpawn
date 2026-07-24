/* CrownPlugins - CrownCore */
/* 21.08.2024 - 02:38 */

package de.crown.spawn.common.listener;

import de.crown.spawn.common.PluginConfig;
import de.obey.crown.core.handler.LocationHandler;
import de.obey.crown.core.noobf.CrownCore;
import de.obey.crown.core.util.Scheduler;
import io.canvasmc.canvas.event.PlayerRespawnAsyncEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

@RequiredArgsConstructor
public final class CanvasPlayerRespawn implements Listener {

    private final PluginConfig pluginConfig;

    @EventHandler
    public void on(final PlayerRespawnAsyncEvent event) {
        if(!pluginConfig.isTeleportToSpawnOnRespawn())
            return;

        final Location spawn = LocationHandler.getLocation("spawn");

        if (spawn == null)
            return;

        event.setRespawnLocation(spawn);
    }
}
