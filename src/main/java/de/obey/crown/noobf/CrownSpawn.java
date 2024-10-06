/* CrownPlugins - ${PROJECT_NAME} */
/* ${DATE} - ${TIME} */

package de.obey.crown.noobf;

import de.obey.crown.command.SpawnCommand;
import de.obey.crown.listener.CoreStart;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CrownSpawn extends JavaPlugin {

    public static CrownSpawn getInstance() {
        return getPlugin(CrownSpawn.class);
    }

    @Override
    public void onEnable() {
        new PluginConfig(this);

        final PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new CoreStart(this), this);
        getCommand("spawn").setExecutor(new SpawnCommand());
    }
}
