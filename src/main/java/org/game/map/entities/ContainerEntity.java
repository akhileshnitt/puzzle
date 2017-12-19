package org.game.map.entities;

public class ContainerEntity extends SimpleEntity {
    private String name;
    private Entity innerEntity;


    public ContainerEntity(String name, EntityType type, int attachPower) {
        super(name, type, attachPower);
    }


    @Override
    public void take(Entity anotherEntity) {
        innerEntity = anotherEntity;
    }
}
