/* CrownPlugins - CrownSpawn */
/* 06.10.2024 - 05:13 */

package de.obey.crown.listener;

import de.obey.crown.core.event.CoreStartEvent;
import de.obey.crown.noobf.CrownSpawn;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor @NonNull
public final class CoreStart implements Listener {

    private final String hi = "https://dsc.gg/crownplugins";
    private final String how = "https://dsc.gg/crownplugins";
    private final String are = "https://dsc.gg/crownplugins";
    private final String you = "https://dsc.gg/crownplugins";
    private final String doing = "https://dsc.gg/crownplugins";

    private final CrownSpawn crownSpawn;

    @EventHandler
    public void on(final CoreStartEvent event) {
        Bukkit.getLogger().info("[^] Thank you for using " + crownSpawn.getName() + " made by Obey!");
    }
}
