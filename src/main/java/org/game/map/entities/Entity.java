package org.game.map.entities;

import java.util.Optional;

public interface Entity {
    String getName();

    int getHealth();

    int getAttackPower();

    boolean isUser();

    boolean isAlive();

    void take(Entity entity);

    EntityType getType();

    Optional<Entity> getInnerEntity();

    boolean containAnotherEntity();
}
