/* CrownPlugins - ${PROJECT_NAME} */
/* ${DATE} - ${TIME} */

package de.obey.crown.noobf;

import de.obey.crown.command.SpawnCommand;
import de.obey.crown.core.data.plugin.Messanger;
import de.obey.crown.listener.CoreStart;
import de.obey.crown.listener.PlayerDeath;
import de.obey.crown.listener.PlayerJoin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CrownSpawn extends JavaPlugin {

    public static CrownSpawn getInstance() {
        return getPlugin(CrownSpawn.class);
    }

    private PluginConfig pluginConfig;
    private Messanger messanger;

    @Override
    public void onEnable() {
        pluginConfig = new PluginConfig(this);
        messanger = pluginConfig.getMessanger();

        final PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new CoreStart(this), this);
        pluginManager.registerEvents(new PlayerDeath(pluginConfig), this);
        pluginManager.registerEvents(new PlayerJoin(pluginConfig), this);

        final SpawnCommand spawnCommand = new SpawnCommand(pluginConfig, messanger);

        getCommand("spawn").setExecutor(spawnCommand);
        getCommand("spawn").setTabCompleter(spawnCommand);
    }
}
