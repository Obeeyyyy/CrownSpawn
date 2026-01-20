package de.crown.spawn.common.adapter;


/*
    Author: Obey
    Date: 18.01.2026
    Time: 19:40
    Project: CrownSpawn
*/

import org.bukkit.Location;

public interface SpawnAdapter {

    void register();

    void applySpawnTimeLock(final Location spawnLocation);

}
