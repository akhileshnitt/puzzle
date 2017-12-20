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

        while(gameMap.containsUserCharacter() || gameMap.containsTasks()) {
            gameMap.goToNextIteration();
            gameView.draw(gameMap);
        }


        if(gameMap.containsUserCharacter()){
            System.out.println("you are winner");
        }
        else{
            System.out.println("Game Over");
        }
        // logic to start game and declared winner

    }
}
