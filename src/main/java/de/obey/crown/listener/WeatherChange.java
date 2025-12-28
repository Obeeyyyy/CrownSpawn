package de.obey.crown.listener;

import de.obey.crown.core.handler.LocationHandler;
import de.obey.crown.noobf.PluginConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

@RequiredArgsConstructor
public class WeatherChange implements Listener {

    final PluginConfig pluginConfig;

    @EventHandler
    public void on(final WeatherChangeEvent event) {

        if(!pluginConfig.isWeatherLock())
            return;


        final Location spawn = LocationHandler.getLocation("spawn");

        if(spawn == null)
            return;

        if(event.getWorld() != spawn.getWorld())
            return;

        event.setCancelled(true);
        spawn.getWorld().setThundering(pluginConfig.isWeatherThunder());
        spawn.getWorld().setStorm(pluginConfig.isWeatherStorm());

    }

}
