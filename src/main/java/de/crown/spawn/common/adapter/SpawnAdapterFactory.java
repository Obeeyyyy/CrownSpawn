package de.crown.spawn.common.adapter;


/*
    Author: Obey
    Date: 18.01.2026
    Time: 19:48
    Project: CrownSpawn
*/

import de.crown.spawn.common.PluginConfig;
import org.bukkit.plugin.Plugin;

public class SpawnAdapterFactory {

    public static SpawnAdapter create(final Plugin plugin, final PluginConfig pluginConfig) {
        if (supportsAsyncEvent())
            return createAsync(plugin, pluginConfig);

        return createSync(plugin, pluginConfig);
    }

    private static boolean supportsAsyncEvent() {
        try {
            Class.forName("io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent", false, SpawnAdapterFactory.class.getClassLoader());
            return true;

        } catch (final ClassNotFoundException ignored) {
            return false;
        }
    }

    private static SpawnAdapter createAsync(final Plugin plugin, final PluginConfig pluginConfig) {
        try {

            final Class<?> clazz = Class.forName("de.crown.spawn.v1_21_10.Logic");
            return (SpawnAdapter) clazz.getConstructor(Plugin.class, PluginConfig.class).newInstance(plugin, pluginConfig);

        } catch (final Exception exception) {
            throw new IllegalStateException("failed to load async spawn adapter", exception);
        }
    }

    private static SpawnAdapter createSync(final Plugin plugin, final PluginConfig pluginConfig) {
        try {

            final Class<?> clazz = Class.forName("de.crown.spawn.v1_20_1.Logic");
            return (SpawnAdapter) clazz.getConstructor(Plugin.class, PluginConfig.class).newInstance(plugin, pluginConfig);

        } catch (final Exception exception) {
            throw new IllegalStateException("Failed to load sync spawn adapter", exception);
        }
    }
}