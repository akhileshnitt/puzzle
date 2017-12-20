package org.game.map.entities;

import java.util.Optional;

public class SimpleEntity implements Entity{

    private String name;
    private EntityType type;
    private int attachPower;

    public SimpleEntity(String name, EntityType type, int attachPower) {
        this.name = name;
        this.type = type;
        this.attachPower = attachPower;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return 0;
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
        return false;
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
    public void take(Entity anotherEntity) {
        throw new UnsupportedOperationException("This method is not supported.");
    }
}
