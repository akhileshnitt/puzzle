package org.game.map.entities;

import java.util.Optional;
import java.util.function.Predicate;

public interface Entity {
    String getName();

    int getHealth();

    int getAttackPower();

    boolean isUser();
    boolean isDefended();

    boolean isAlive();

    void take(Entity entity);

    EntityType getType();

    Optional<Entity> getInnerEntity();

    boolean containAnotherEntity();

    boolean containUserCharacter();

    boolean canContainAnotherEntity();

    boolean containTasks(Predicate<Entity> condition);

    Entity findEntity(Predicate<Entity> condition);

    void clear();

    int isBeatenBy(Entity user);

    void relax();

    void defense();
}
