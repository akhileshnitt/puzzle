package org.game.map.factory;

import org.game.map.GameMap;
import org.game.map.GameMapBuilder;
import org.game.map.MainGameMap;
import org.game.map.MapFactory;
import org.game.map.entities.Entity;
import org.game.map.entities.character.NewCharacterFactory;


import java.util.List;

import static org.game.map.GameMapBuilder.map;
import static org.game.map.entities.EntityFactory.*;
import static org.game.map.entities.character.Race.ORC;
import static org.game.map.entities.character.Sex.FEMALE;

public class StaticMapFactory implements MapFactory{


    private final NewCharacterFactory newCharacterFactory;


    public StaticMapFactory(NewCharacterFactory newCharacterFactory) {
        this.newCharacterFactory = newCharacterFactory;
    }

    @Override
    public GameMap create() {
        return create(entities(newCharacterFactory.getGameCharacter()));
    }

    private GameMap create(List<List<Entity>> entities) {
        return new MainGameMap(entities);
    }

    private List<List<Entity>> entities(Entity character) {
        return map()
                .line(road(), road(), road(wolf()), tree(), stone())
                .line(road(), road(), road(), tree(), tree())
                .line(road(), road(), road(character), road(), road(bear()))
                .line(road(), stone(), road(), road(), road())
                .line(road(orc()), tree(), road(), road(), road())
                .create();
    }



    private static Entity orc() {
        return character(ORC.toString(), ORC, FEMALE, 10);
    }




    @Override
    public GameMap restore() {
        return null;
    }
}
