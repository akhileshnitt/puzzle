package org.game.map.entities;

import java.util.Optional;
import java.util.function.Predicate;

public class SimpleEntity implements Entity{

    private String name;
    private EntityType type;
    private int attachPower;
    private int health;

    public SimpleEntity(String name, EntityType type, int attachPower) {
        this.name = name;
        this.type = type;
        this.attachPower = attachPower;
        this.health = 100;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttackPower() {
        return attachPower;
    }

    @Override
    public boolean isUser() {
        return false;
    }

    @Override
    public boolean isAlive() {
        return health>0;
    }

    @Override
    public Optional<Entity> getInnerEntity() {
        return Optional.empty();
    }

    @Override
    public EntityType getType() {
        return type;
    }

    @Override
    public boolean containAnotherEntity() {
        return false;
    }

    @Override
    public boolean containUserCharacter() {
        return getInnerEntity().filter(entity -> entity.isUser()||entity.containUserCharacter()).isPresent();
    }

    @Override
    public boolean canContainAnotherEntity() {
        return false;
    }

    @Override
    public void take(Entity anotherEntity) {
        throw new UnsupportedOperationException("This method is not supported.");
    }


    @Override
    public boolean containTasks(Predicate<Entity> condition) {
        return condition.test(this) && !isUser()
                ||getInnerEntity().filter(entity -> entity.containTasks(condition)).isPresent();
    }


    @Override
    public Entity findEntity(Predicate<Entity> condition) {
        return getInnerEntity().map(entity -> condition.test(entity)?entity:entity.findEntity(condition))
                .orElseThrow(()->new IllegalStateException("There is no entities with such condition"));
    }

    @Override
    public void clear() {
        throw  new UnsupportedOperationException("this operation is not supported");
    }
}
