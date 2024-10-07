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

    private boolean teleportToSpawnOnJoin, isTeleportToSpawnOnFirstJoin, instantRespawn;

    public PluginConfig(@NonNull Plugin plugin) {
        super(plugin);
    }

    @Override
    public void loadConfig() {
        final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(getConfigFile());

        teleportToSpawnOnJoin = FileUtil.getBoolean(configuration, "teleport-to-spawn-on-join", false);
        isTeleportToSpawnOnFirstJoin = FileUtil.getBoolean(configuration, "teleport-to-spawn-on-first-join", true);
        instantRespawn = FileUtil.getBoolean(configuration, "instant-respawn", true);
    }
}
