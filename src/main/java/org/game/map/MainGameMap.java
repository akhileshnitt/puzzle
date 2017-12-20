package org.game.map;

import org.game.map.behaviour.user.UserMovementInput;
import org.game.map.entities.Entity;
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

    public MainGameMap(List<List<Entity>> entities, UserMovementInput userMovementInput) {
        this.entities =entities;
        this.userMovementInput = userMovementInput;
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
