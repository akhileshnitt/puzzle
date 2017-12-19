package org.game;

import org.game.common.mvp.Presenter;
import org.game.map.MapFactory;
import org.game.menu.MainMainMenuConsoleConsoleView;
import org.game.menu.MainMenuPresenter;
import org.game.play.GameFactory;
import org.game.play.GameFactoryImpl;
import org.game.play.GameView;

public class EntryPointFactory {
    private EntryPointFactory(){}

    public static Presenter newEntryPoint() {

        return  new MainMenuPresenter(new MainMainMenuConsoleConsoleView(), gameFactory());
    }

    private static GameFactory gameFactory() {
        return new GameFactoryImpl(mapFactory(), gameView());
    }

    private static GameView gameView() {
        return null;
    }

    private static MapFactory mapFactory() {
        return null;
    }
}
