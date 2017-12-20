package org.game.map.entities;

import org.game.map.entities.character.Race;
import org.game.map.entities.character.Sex;

import static org.game.map.entities.EntityType.*;

public final class EntityFactory {
    private EntityFactory(){}


    public static Entity road(Entity entity) {
        Entity road = road();
        road.take(entity);
        return road;
    }


    public static Entity road(){
        return new ContainerEntity("Road", ROAD, 0);
    }

    public static Entity wolf() {
        return new SimpleEntity("Wolf", WOLF, 10);
    }

    public static Entity bear() {
        return new SimpleEntity("Bear", BEAR, 20);
    }

    public static Entity tree() {
        return new SimpleEntity("Tree", TREE, 0);
    }

    public static Entity stone() {
        return new SimpleEntity("Stone", STONE, 0);
    }



    public static Entity character(String name, Race race, Sex sex) {
        return character(name, race, sex, 30);
    }


    public static Entity character(String name, Race race, Sex sex, int attackPower) {
        return new GameCharacter(name, false, race, sex, attackPower);
    }


    public static  Entity userCharacter(String name, Race race, Sex sex) {
        return new GameCharacter(name, true, race, sex, 50);
    }
}
