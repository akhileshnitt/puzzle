package org.game.map.entities;

import java.util.Optional;

public class ContainerEntity extends SimpleEntity {

    private Entity innerEntity;


    public ContainerEntity(String name, EntityType type, int attachPower) {
        super(name, type, attachPower);
    }


    @Override
    public void take(Entity anotherEntity) {
        innerEntity = anotherEntity;
    }



    @Override
    public Optional<Entity> getInnerEntity() {
        return Optional.ofNullable(innerEntity);
    }


    @Override
    public boolean containAnotherEntity() {
        return getInnerEntity().isPresent();
    }


    @Override
    public boolean canContainAnotherEntity() {
        return true;
    }

    @Override
    public void clear() {
        innerEntity = null;
    }
}
