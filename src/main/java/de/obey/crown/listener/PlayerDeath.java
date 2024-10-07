/* CrownPlugins - CrownCore */
/* 21.08.2024 - 02:38 */

package de.obey.crown.listener;

import de.obey.crown.core.CrownCore;
import de.obey.crown.core.handler.LocationHandler;
import de.obey.crown.noobf.PluginConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

@RequiredArgsConstructor
@NonNull
public final class PlayerDeath implements Listener {

    private final String hi = "https://dsc.gg/crownplugins";
    private final String how = "https://dsc.gg/crownplugins";
    private final String are = "https://dsc.gg/crownplugins";
    private final String you = "https://dsc.gg/crownplugins";
    private final String doing = "https://dsc.gg/crownplugins";

    private final PluginConfig pluginConfig;

    @EventHandler
    public void on(final PlayerDeathEvent event) {

        if (pluginConfig.isInstantRespawn()) {
            Bukkit.getScheduler().runTaskLater(CrownCore.getInstance(), () -> {
                event.getEntity().spigot().respawn();
            }, 2);
        }
    }

    @EventHandler
    public void on(final PlayerRespawnEvent event) {
        final Location spawn = LocationHandler.getLocation("spawn");

        if (spawn == null)
            return;

        event.setRespawnLocation(spawn);
    }
}
