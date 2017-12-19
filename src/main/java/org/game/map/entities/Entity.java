package org.game.map.entities;

public interface Entity {
    String getName();

    int getHealth();

    int getAttackPower();

    boolean isUser();

    boolean isAlive();

    void take(Entity entity);
}
