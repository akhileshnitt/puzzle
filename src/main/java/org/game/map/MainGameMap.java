package org.game.map;

import org.game.map.entities.Entity;

import java.util.List;

public class MainGameMap implements GameMap{


    public MainGameMap(List<List<Entity>> entities) {
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
}
