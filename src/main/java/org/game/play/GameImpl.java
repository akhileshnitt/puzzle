package org.game.play;

import org.game.common.mvp.console.ui.utils.AsciiHelper;
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
        // logic to start game and declared winner
        gameView.draw(gameMap);

        while(gameMap.containsUserCharacter() && gameMap.containsTasks()) {
            gameMap.goToNextIteration();
            gameView.draw(gameMap);
        }


        if(gameMap.containsUserCharacter()){
            System.out.println(AsciiHelper.ANSI_BLUE + "!!!!!!!    You are winner !!!!!!!!!!"+AsciiHelper.ANSI_RESET);
        }
        else{
            System.out.println(AsciiHelper.ANSI_RED+ "-----   Game Over   ----- "+AsciiHelper.ANSI_RESET);
        }


    }
}
