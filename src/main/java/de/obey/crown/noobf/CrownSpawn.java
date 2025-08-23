/* CrownPlugins - ${PROJECT_NAME} */
/* ${DATE} - ${TIME} */

package de.obey.crown.noobf;

import de.obey.crown.command.SpawnCommand;
import de.obey.crown.core.data.plugin.Messanger;
import de.obey.crown.core.handler.LocationHandler;
import de.obey.crown.listener.*;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CrownSpawn extends JavaPlugin {

    public static CrownSpawn getInstance() {
        return getPlugin(CrownSpawn.class);
    }

    private PluginConfig pluginConfig;
    private Messanger messanger;

    @Override
    public void onLoad() {
        pluginConfig = new PluginConfig(this);
        messanger = pluginConfig.getMessanger();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CoreStart(this), this);
    }

    public void load() {
        final PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerDeath(pluginConfig), this);
        pluginManager.registerEvents(new PlayerJoin(pluginConfig), this);
        pluginManager.registerEvents(new PlayerMove(pluginConfig), this);
        pluginManager.registerEvents(new WeatherChange(pluginConfig), this);

        final SpawnCommand spawnCommand = new SpawnCommand(pluginConfig, messanger);

        Objects.requireNonNull(getCommand("spawn")).setExecutor(spawnCommand);
        Objects.requireNonNull(getCommand("spawn")).setTabCompleter(spawnCommand);

        applySpawnTimeLock();
    }

    public void applySpawnTimeLock() {
        if(pluginConfig == null) {
            return;
        }

        final Location spawn = LocationHandler.getLocation("spawn");

        if(spawn == null) {
            return;
        }

        spawn.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, !pluginConfig.isTimeLock());
        spawn.getWorld().setTime(pluginConfig.getTimeValue());
    }
}
