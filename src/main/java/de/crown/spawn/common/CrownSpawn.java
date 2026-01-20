/* CrownPlugins - ${PROJECT_NAME} */
/* ${DATE} - ${TIME} */

package de.crown.spawn.common;

import de.crown.spawn.common.adapter.SpawnAdapter;
import de.crown.spawn.common.adapter.SpawnAdapterFactory;
import de.crown.spawn.common.command.SpawnCommand;
import de.obey.crown.core.data.plugin.Messanger;
import de.obey.crown.core.handler.LocationHandler;
import de.crown.spawn.common.listener.*;
import org.bstats.bukkit.Metrics;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CrownSpawn extends JavaPlugin {

    public static CrownSpawn getInstance() {
        return getPlugin(CrownSpawn.class);
    }

    private SpawnAdapter spawnAdapter;

    private PluginConfig pluginConfig;
    private Messanger messanger;

    @Override
    public void onLoad() {
        pluginConfig = new PluginConfig(this);
        messanger = pluginConfig.getMessanger();
    }

    @Override
    public void onEnable() {
        spawnAdapter = SpawnAdapterFactory.create(this, pluginConfig);

        getServer().getPluginManager().registerEvents(new CoreStart(this), this);

        initializeBStats();
    }

    /***
     * Initializes metrics for bStats
     */
    private void initializeBStats()  {
        new Metrics(this, 27338);
    }


    public void load() {
        final PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerDeath(pluginConfig), this);
        pluginManager.registerEvents(new PlayerMove(pluginConfig), this);
        pluginManager.registerEvents(new WeatherChange(pluginConfig), this);

        spawnAdapter.register();

        final SpawnCommand spawnCommand = new SpawnCommand(pluginConfig, messanger);

        Objects.requireNonNull(getCommand("spawn")).setExecutor(spawnCommand);
        Objects.requireNonNull(getCommand("spawn")).setTabCompleter(spawnCommand);

        applySpawnTimeLock();
    }

    public void applySpawnTimeLock() {
        if(pluginConfig == null)
            return;

        final Location spawn = LocationHandler.getLocation("spawn");

        if(spawn == null)
            return;

        if(!pluginConfig.isTimeLock())
            return;

        spawnAdapter.applySpawnTimeLock(spawn);
    }
}
