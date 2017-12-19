package org.game.map;

public interface MapFactory {
    GameMap create();

    GameMap restore();
}
