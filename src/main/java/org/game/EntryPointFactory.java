package org.game;

import org.game.common.mvp.Presenter;
import org.game.map.MapFactory;
import org.game.map.behaviour.user.UserMovementConsoleInput;
import org.game.map.behaviour.user.UserMovementInput;
import org.game.map.entities.Entity;
import org.game.map.entities.EntityType;
import org.game.map.entities.character.NewCharacterConsoleConsoleView;
import org.game.map.entities.character.NewCharacterPresenter;
import org.game.map.factory.StaticMapFactory;
import org.game.map.task.TaskCompletionStrategy;
import org.game.map.task.fight.FightConsoleView;
import org.game.map.task.fight.FightStrategy;
import org.game.menu.MainMainMenuConsoleConsoleView;
import org.game.menu.MainMenuPresenter;
import org.game.play.GameConsoleView;
import org.game.play.GameFactory;
import org.game.play.GameFactoryImpl;
import org.game.play.GameView;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;

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
        return new StaticMapFactory(characterPresenter(),userMovementInput(), taskDetectionCondition(),taskCompletionStrategy());
    }

    private static TaskCompletionStrategy taskCompletionStrategy() {
        return new FightStrategy(new FightConsoleView());
    }

    private static Predicate<Entity> taskDetectionCondition(){
        Set<EntityType> enemies = EnumSet.of(EntityType.CHARACTER,EntityType.BEAR,EntityType.WOLF);
        return entity -> enemies.contains(entity);
    }

    private static UserMovementInput userMovementInput() {
        return new UserMovementConsoleInput();
    }


    private static NewCharacterPresenter characterPresenter() {
        return new NewCharacterPresenter(new NewCharacterConsoleConsoleView());
    }

}
