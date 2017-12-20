package org.game.play;

import org.game.map.GameMap;

public class GameImpl implements Game {

    private GameMap gameMap;
    private GameView gameView;
    public GameImpl(GameMap gameMap, GameView gameView) {
        this.gameMap = gameMap;
        this.gameView = gameView;
    }

    @Override
    public void start() {
        gameView.draw(gameMap);

        gameMap.goToNextIteration();
        gameView.draw(gameMap);

        // logic to start game and declared winner

    }
}
