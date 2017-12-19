package org.game.map;

import org.game.map.entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class GameMapBuilder {
   private  GameMapBuilder(){}

   private List<List<Entity>> entities = new ArrayList<>();


    public static GameMapBuilder map() {
        return new GameMapBuilder();
    }

    public GameMapBuilder line(Entity... entity) {
          this.entities.add(asList(entity));
          return this;
    }

    public List<List<Entity>> create() {
        return Collections.unmodifiableList(entities);
    }
}
