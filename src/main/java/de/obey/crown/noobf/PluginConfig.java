/* CrownPlugins - CrownSpawn */
/* 06.10.2024 - 05:11 */

package de.obey.crown.noobf;

import de.obey.crown.core.data.plugin.CrownConfig;
import de.obey.crown.core.util.FileUtil;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

@Getter
public final class PluginConfig extends CrownConfig {

    private final String hi = "https://dsc.gg/crownplugins";
    private final String how = "https://dsc.gg/crownplugins";
    private final String are = "https://dsc.gg/crownplugins";
    private final String you = "https://dsc.gg/crownplugins";
    private final String doing = "https://dsc.gg/crownplugins";

    private boolean teleportToSpawnOnJoin, isTeleportToSpawnOnFirstJoin, instantRespawn, teleportToSpawnWhenUnder, teleportToSpawnOnRespawn, weatherLock, timeLock, weatherThunder, weatherStorm;
    private int underY, timeValue;


    public PluginConfig(@NonNull Plugin plugin) {
        super(plugin);
    }

    @Override
    public void loadConfig() {
        final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(getConfigFile());

        teleportToSpawnOnJoin = FileUtil.getBoolean(configuration, "teleport-to-spawn-on-join", false);
        isTeleportToSpawnOnFirstJoin = FileUtil.getBoolean(configuration, "teleport-to-spawn-on-first-join", true);
        instantRespawn = FileUtil.getBoolean(configuration, "instant-respawn", true);
        teleportToSpawnOnRespawn = FileUtil.getBoolean(configuration, "teleport-to-spawn-on-respawn", true);
        teleportToSpawnWhenUnder = FileUtil.getBoolean(configuration, "teleport-to-spawn-when-under.enabled", false);
        weatherLock = FileUtil.getBoolean(configuration, "lock.weather.enabled", false);
        weatherThunder = FileUtil.getBoolean(configuration, "lock.weather.thunder", false);
        weatherStorm = FileUtil.getBoolean(configuration, "lock.weather.storm", false);
        timeLock = FileUtil.getBoolean(configuration, "lock.time.enabled", false);


        underY = FileUtil.getInt(configuration, "teleport-to-spawn-when-under.under-y", 0);
        timeValue = FileUtil.getInt(configuration, "lock.time.value", 1000);

        FileUtil.saveConfigurationIntoFile(configuration, getConfigFile());
        CrownSpawn.getInstance().applySpawnTimeLock();
    }
}
