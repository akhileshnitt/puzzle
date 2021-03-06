package org.game.map.factory;

import org.game.map.GameMap;
import org.game.map.GameMapBuilder;
import org.game.map.MainGameMap;
import org.game.map.MapFactory;
import org.game.map.behaviour.user.UserMovementConsoleInput;
import org.game.map.behaviour.user.UserMovementInput;
import org.game.map.entities.Entity;
import org.game.map.entities.character.NewCharacterFactory;
import org.game.map.task.TaskCompletionStrategy;


import java.util.List;
import java.util.function.Predicate;

import static org.game.map.GameMapBuilder.map;
import static org.game.map.entities.EntityFactory.*;
import static org.game.map.entities.character.Race.ORC;
import static org.game.map.entities.character.Sex.FEMALE;

public class StaticMapFactory implements MapFactory{


    private final NewCharacterFactory newCharacterFactory;
    private final UserMovementInput userMovementInput;
    private final Predicate<Entity> taskDetectionCondition;
    private final TaskCompletionStrategy taskCompletionStrategy;


    public StaticMapFactory(NewCharacterFactory newCharacterFactory,
                            UserMovementInput userMovementInput,
                            Predicate<Entity> taskDetectionCondition,
                            TaskCompletionStrategy taskCompletionStrategy) {
        this.newCharacterFactory = newCharacterFactory;
        this.userMovementInput = userMovementInput;
        this.taskDetectionCondition = taskDetectionCondition;
        this.taskCompletionStrategy = taskCompletionStrategy;
    }

    @Override
    public GameMap create() {
        return create(entities(newCharacterFactory.getGameCharacter()));
    }

    private GameMap create(List<List<Entity>> entities) {
        return new MainGameMap(entities,userMovementInput,taskDetectionCondition,taskCompletionStrategy);
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
