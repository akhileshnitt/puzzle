package org.game.map;

import org.game.map.entities.Entity;

import java.util.List;

public interface GameMap {
    boolean containsUserCharacter();

    boolean containsTasks();

    void goToNextIteration();

    List<List<Entity>> getEntities();
}
