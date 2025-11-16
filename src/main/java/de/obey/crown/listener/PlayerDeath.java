/* CrownPlugins - CrownCore */
/* 21.08.2024 - 02:38 */

package de.obey.crown.listener;

import de.obey.crown.core.handler.LocationHandler;
import de.obey.crown.core.noobf.CrownCore;
import de.obey.crown.core.util.Scheduler;
import de.obey.crown.noobf.PluginConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

@RequiredArgsConstructor
public final class PlayerDeath implements Listener {

    private final PluginConfig pluginConfig;

    @EventHandler
    public void on(final PlayerDeathEvent event) {

        if (pluginConfig.isInstantRespawn()) {
            Scheduler.runGlobalTaskLater(CrownCore.getInstance(), () -> event.getEntity().spigot().respawn(), 2);
        }
    }

    @EventHandler
    public void on(final PlayerRespawnEvent event) {
        if(!pluginConfig.isTeleportToSpawnOnRespawn())
            return;

        final Location spawn = LocationHandler.getLocation("spawn");

        if (spawn == null)
            return;

        event.setRespawnLocation(spawn);
    }
}
