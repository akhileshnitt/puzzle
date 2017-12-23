package org.game.map;

import org.game.map.behaviour.user.UserMovementInput;
import org.game.map.entities.Entity;
import org.game.map.task.TaskCompletionStrategy;
import org.game.map.utils.IntRange;
import org.game.map.utils.Position;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class MainGameMap implements GameMap{


    private  List<List<Entity>> entities;
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
        return entities().anyMatch(Entity::containUserCharacter);
    }

    @Override
    public boolean containsTasks() {
        return entities().anyMatch(entity -> entity.containTasks(taskDetectionCondition));
    }

    private Stream<Entity> entities() {
        return entities.stream().flatMap(List::stream);
    }

    @Override
    public void goToNextIteration() {
        Position currentPosition = findFirstEntity(Entity::containUserCharacter)
                .orElseThrow(() -> new IllegalStateException("It is impossible to continue game when no user character on the map"));
        Position nextPosition = userMovementInput.getNextPosition(currentPosition);
        if (isValid(nextPosition, entities.size() - 1)) {
            moveUser(currentPosition, nextPosition);
        }
        entities().forEach(Entity::relax);
    }

    private Optional<Position> findFirstEntity(Predicate<Entity> condition) {
        return range(0, entities.size())
                .boxed()
                .flatMap(top -> zip(top, findEntityIndex(entities.get(top), condition)))
                .findFirst();
    }

    private IntStream findEntityIndex(List<Entity> entities, Predicate<Entity> condition) {
        return range(0, entities.size()).filter(left -> condition.test(entities.get(left)));
    }

    private Stream<Position> zip(int top, IntStream leftCoordinates) {
        return leftCoordinates.mapToObj(left -> Position.of(left, top));
    }

    private boolean isValid(Position position, int maxCoordinate) {
        IntRange correctCoordinate = IntRange.of(0, maxCoordinate);
        return correctCoordinate.contains(position.getLeft())
                && correctCoordinate.contains(position.getTop());
    }

    private void moveUser(Position currentPosition, Position nextPosition) {
        Entity containerEntity = entityOn(currentPosition);
        Entity newContainerEntity = entityOn(nextPosition);

        if (!newContainerEntity.canContainAnotherEntity()) {
            return;
        }

        if (newContainerEntity.containTasks(taskDetectionCondition)) {
            Entity userCharacter = containerEntity.findEntity(Entity::isUser);
            Entity taskEntity = newContainerEntity.findEntity(taskDetectionCondition);

            taskCompletionStrategy.complete(userCharacter, taskEntity);
        }

        containerEntity.getInnerEntity().ifPresent(userCharacter -> {
            containerEntity.clear();
            newContainerEntity.take(userCharacter);
        });

        if (!isUserAlive(newContainerEntity)) {
            newContainerEntity.clear();
        }
    }

    private Entity entityOn(Position position) {
        return entities.get(position.getTop()).get(position.getLeft());
    }

    private boolean isUserAlive(Entity entity) {
        return entity.findEntity(Entity::isUser).isAlive();
    }

    @Override
    public List<List<Entity>> getEntities() {
        return entities;
    }
}
