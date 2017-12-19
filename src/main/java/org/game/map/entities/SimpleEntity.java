package org.game.map.entities;

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
    public void take(Entity anotherEntity) {
        throw new UnsupportedOperationException("This method is not supported.");
    }
}
