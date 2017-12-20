package org.game.play;

import org.game.common.mvp.View;
import org.game.map.GameMap;

public interface GameView extends View{
    void draw(GameMap map);

    void showWinnerNotification();

    void showGameOverNotification();
}
