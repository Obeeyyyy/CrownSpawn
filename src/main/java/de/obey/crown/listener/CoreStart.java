/* CrownPlugins - CrownSpawn */
/* 06.10.2024 - 05:13 */

package de.obey.crown.listener;

import de.obey.crown.core.event.CoreStartEvent;
import de.obey.crown.noobf.CrownSpawn;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public final class CoreStart implements Listener {
    private final CrownSpawn crownSpawn;

    @EventHandler
    public void on(final CoreStartEvent event) {
        event.sendStartupMessage(crownSpawn);
        crownSpawn.load();
    }
}
