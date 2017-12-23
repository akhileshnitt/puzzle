package org.game.map.task.fight;

import org.game.common.mvp.console.AbstractConsoleView;
import org.game.common.mvp.console.ui.Menu;
import org.game.common.mvp.console.ui.utils.AsciiHelper;
import org.game.map.entities.Entity;
import org.game.map.entities.GameCharacter;

import static java.lang.String.format;

public class FightConsoleView extends AbstractConsoleView<FightView.ActionDeletegate> implements FightView {

   private static final Menu<FightAction> fightMenu = new Menu<FightAction>("Choose your action:", FightAction.values());


    @Override
    public void drawUser(Entity user) {
        drawEntity(user);

    }

    private void drawEntity(Entity entity) {

        if(entity instanceof GameCharacter){
            GameCharacter character = (GameCharacter) entity;
            System.out.println(format(
                    "Name: %s; Race: %s; Sex: %s; Health: %d; Attack power: %d",
                    character.getName(), character.getRace(), character.getSex(), character.getHealth(), character.getAttackPower()

            ));
        }
        else{
            System.out.println(format(
                    "Name: %s; Type: %s; Health: %d; Attack power: %d",
                    entity.getName(), entity.getType(), entity.getHealth(), entity.getAttackPower()
            ));
        }


    }

    @Override
    public void drawEnemy(Entity enemy) {
        drawEntity(enemy);
    }


    @Override
    public void drawAttack(Entity attacker, Entity defender, int damage) {

        System.out.println(AsciiHelper.ANSI_RED+format(
                "%s attacks %s. %s got a damage  :: %d",
                attacker.getName(), defender.getName(), defender.getName(), damage
        )+AsciiHelper.ANSI_RESET);

    }

    @Override
    public void draw() {
        fightMenu.draw();
        switch (fightMenu.chooseItem()){
            case BEAT:
              delegate.onUserAttack();
              break;
            case DEFENCE:
                delegate.onUserDefend();
              break;
            case DONOTHING:
                delegate.onDoNothing();
              break;
            default:
        }

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
