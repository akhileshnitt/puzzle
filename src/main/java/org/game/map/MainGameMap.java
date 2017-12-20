package org.game.map;

import org.game.map.behaviour.user.UserMovementInput;
import org.game.map.entities.Entity;
import org.game.map.task.TaskCompletionStrategy;
import org.game.map.utils.Position;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class MainGameMap implements GameMap{


    private final List<List<Entity>> entities;
    private UserMovementInput userMovementInput;
    private final Predicate<Entity> taskDetectionCondition;
    private final TaskCompletionStrategy taskCompletionStrategy;

    public MainGameMap(List<List<Entity>> entities, UserMovementInput userMovementInput,Predicate<Entity> taskDetectionCondition,
                       TaskCompletionStrategy taskCompletionStrategy) {
        this.entities =entities;
        this.userMovementInput = userMovementInput;
        this.taskDetectionCondition = taskDetectionCondition;
        this.taskCompletionStrategy = taskCompletionStrategy;
    }

    @Override
    public boolean containsUserCharacter() {
        return false;
    }

    @Override
    public boolean containsTasks() {
        return false;
    }

    @Override
    public void goToNextIteration() {

        Position currentPosition = findFirstEntity(Entity::containUserCharacter)
                .orElseThrow(()-> new IllegalStateException("It is impossible to continue game when no user character on the map"));
        Position nextPosition = userMovementInput.getNextPosition(currentPosition);
        if(isValid(nextPosition,entities.size()-1)){
          //  System.out.println("valid move");
            moveUser(currentPosition, nextPosition);
        }

    }

    private void moveUser(Position currentPosition, Position nextPosition) {
        Entity containerEntity = entityOn(currentPosition);
        Entity newContainerEntity = entityOn(nextPosition);
        if(!newContainerEntity.canContainAnotherEntity()){
            return;
        }

        if(newContainerEntity.containTasks(taskDetectionCondition)){
            Entity userCharacter = containerEntity.findEntity(Entity::isUser);
            Entity taskEntity = newContainerEntity.findEntity(taskDetectionCondition);

            taskCompletionStrategy.complete(userCharacter, taskEntity);

        }

        containerEntity.getInnerEntity().ifPresent(userCharacter->{
           containerEntity.clear();
           newContainerEntity.take(userCharacter);
        });



    }

    private Entity entityOn(Position currentPosition) {
        return entities.get(currentPosition.getTop()).get(currentPosition.getLeft());
    }

    private boolean isValid(Position nextPosition, int maxCoordinate) {
        return (nextPosition.getLeft() >0 && nextPosition.getLeft()<maxCoordinate)&&
                (nextPosition.getTop()>0 && nextPosition.getTop()<maxCoordinate);
    }

    private Optional<Position> findFirstEntity(Predicate<Entity> condition) {
        return range(0,entities.size()).boxed()
                .flatMap(top -> zip(top, findEntityIndex(entities.get(top), condition)))
                .findFirst();

    }

    private Stream<Position> zip(int top, IntStream leftCoordinates) {
        return leftCoordinates.mapToObj(left -> Position.of(left, top));
    }

    private IntStream findEntityIndex(List<Entity> entities, Predicate<Entity> condition) {
        return range(0, entities.size()).filter(left -> condition.test(entities.get(left)));
    }
    @Override
    public List<List<Entity>> getEntities() {
        return entities;
    }
}
