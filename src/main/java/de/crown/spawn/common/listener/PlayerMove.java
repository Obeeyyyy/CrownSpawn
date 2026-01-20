package de.crown.spawn.common.listener;

import de.obey.crown.core.handler.LocationHandler;
import de.obey.crown.core.util.Teleporter;
import de.crown.spawn.common.PluginConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@RequiredArgsConstructor
public class PlayerMove implements Listener {

    private final PluginConfig pluginConfig;

    @EventHandler
    public void on(final PlayerMoveEvent event) {

        if(!pluginConfig.isTeleportToSpawnWhenUnder())
            return;

        final Location spawn = LocationHandler.getLocation("spawn");

        if(spawn == null)
            return;

        if(event.getTo().getWorld() != spawn.getWorld())
            return;

        if(event.getTo().getY() > pluginConfig.getUnderY())
            return;

        Teleporter.teleportInstant(event.getPlayer(), spawn);

    }

}
