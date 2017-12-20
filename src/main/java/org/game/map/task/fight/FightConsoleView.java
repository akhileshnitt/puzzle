package org.game.map.task.fight;

import org.game.common.mvp.console.AbstractConsoleView;
import org.game.common.mvp.console.ui.Menu;
import org.game.map.entities.Entity;

public class FightConsoleView extends AbstractConsoleView<FightView.ActionDeletegate> implements FightView {

   private static final Menu<FightAction> fightMenu = new Menu<FightAction>("Choose your action:", FightAction.values());


    @Override
    public void drawUser(Entity user) {

    }

    @Override
    public void drawEnemy(Entity enemy) {

    }

    @Override
    public void drawAttack(Entity user, Entity enemy) {

    }

    @Override
    public void draw() {

    }


    private enum FightAction{
        BEAT("Beat the opponent"),
        DEFENCE("Defend yourself"),
        DONOTHING("Do Nothing");

        private String title;

        FightAction(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
