package org.game;

import org.game.common.mvp.Presenter;
import org.game.map.MapFactory;
import org.game.map.entities.character.NewCharacterConsoleConsoleView;
import org.game.map.entities.character.NewCharacterPresenter;
import org.game.map.factory.StaticMapFactory;
import org.game.menu.MainMainMenuConsoleConsoleView;
import org.game.menu.MainMenuPresenter;
import org.game.play.GameConsoleView;
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
        return new GameConsoleView();
    }

    private static MapFactory mapFactory() {
        return new StaticMapFactory(characterPresenter());
    }


    private static NewCharacterPresenter characterPresenter() {
        return new NewCharacterPresenter(new NewCharacterConsoleConsoleView());
    }

}
