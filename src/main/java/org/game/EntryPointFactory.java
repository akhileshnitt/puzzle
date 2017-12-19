package org.game;

import org.game.common.mvp.Presenter;
import org.game.menu.MainMainMenuConsoleConsoleView;
import org.game.menu.MainMenuPresenter;
import org.game.play.GameFactory;
import org.game.play.GameFactoryImpl;

public class EntryPointFactory {
    private EntryPointFactory(){}

    public static Presenter newEntryPoint() {

        return  new MainMenuPresenter(new MainMainMenuConsoleConsoleView(), gameFactory());
    }

    private static GameFactory gameFactory() {
        return new GameFactoryImpl();
    }
}
