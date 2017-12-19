package org.game.play;

import org.game.map.GameMap;
import org.game.map.MapFactory;

public class GameFactoryImpl implements GameFactory {

    private MapFactory mapFactory;
    private GameView gameView;
    public GameFactoryImpl(MapFactory mapFactory, GameView gameView) {
        this.mapFactory = mapFactory;
        this.gameView = gameView;
    }

    @Override
    public Game create() {
        return create(mapFactory.create());
    }

    private Game create(GameMap gameMap) {
        return new GameImpl(gameMap, gameView);
    }

    @Override
    public Game resume() {
        return create(mapFactory.restore());
    }
}
