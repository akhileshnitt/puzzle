package org.game.map;

import org.game.map.entities.Entity;

import java.util.List;

public class MainGameMap implements GameMap{


    private final List<List<Entity>> entities;

    public MainGameMap(List<List<Entity>> entities) {
        this.entities =entities;
    }

    @Override
    public boolean containsUserCharacter() {
        return false;
    }

    @Override
    public boolean containsTasks() {
        return false;
    }

    @Override
    public void goToNextIteration() {

    }

    @Override
    public List<List<Entity>> getEntities() {
        return entities;
    }
}
