package org.game.map;

public interface GameMap {
    boolean containsUserCharacter();

    boolean containsTasks();

    void goToNextIteration();
}
