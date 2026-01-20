/* CrownPlugins - CrownSpawn */
/* 07.10.2024 - 04:20 */

package de.crown.spawn.v1_20_1;

import de.crown.spawn.common.adapter.SpawnAdapter;
import de.obey.crown.core.handler.LocationHandler;
import de.crown.spawn.common.PluginConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("removal")
@RequiredArgsConstructor
public final class Logic implements Listener, SpawnAdapter {

    private final Plugin plugin;
    private final PluginConfig pluginConfig;

    @Override
    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void applySpawnTimeLock(final Location spawn) {
        spawn.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, !pluginConfig.isTimeLock());
        spawn.getWorld().setTime(pluginConfig.getTimeValue());
    }

    @EventHandler
    public void on(final org.spigotmc.event.player.PlayerSpawnLocationEvent event) {
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
