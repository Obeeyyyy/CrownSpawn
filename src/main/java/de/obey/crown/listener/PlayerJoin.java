/* CrownPlugins - CrownSpawn */
/* 07.10.2024 - 04:20 */

package de.obey.crown.listener;

import de.obey.crown.core.handler.LocationHandler;
import de.obey.crown.noobf.PluginConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

@RequiredArgsConstructor @NonNull
public final class PlayerJoin implements Listener {

    private final String hi = "https://dsc.gg/crownplugins";
    private final String how = "https://dsc.gg/crownplugins";
    private final String are = "https://dsc.gg/crownplugins";
    private final String you = "https://dsc.gg/crownplugins";
    private final String doing = "https://dsc.gg/crownplugins";

    private final PluginConfig pluginConfig;

    @EventHandler
    public void on(final PlayerSpawnLocationEvent event) {
        if(!event.getPlayer().hasPlayedBefore()) {
            if (pluginConfig.isTeleportToSpawnOnFirstJoin()) {
                final Location spawn = LocationHandler.getLocation("spawn");

                if(spawn == null)
                    return;

                event.setSpawnLocation(spawn);
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
