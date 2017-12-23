package org.game.map.task.fight;

import org.game.common.mvp.AbstractPresenter;
import org.game.common.mvp.console.ui.utils.AsciiHelper;
import org.game.map.entities.Entity;
import org.game.map.task.TaskCompletionStrategy;

public class FightStrategy extends AbstractPresenter<FightView> implements TaskCompletionStrategy,FightView.ActionDeletegate {


    private  Entity user;
    private Entity enemy;



    public FightStrategy(FightView view) {
        super(view);
        this.view.setDelegate(this);
    }

    @Override
    public void complete(Entity user, Entity enemy) {
        this.user = user;
        this.enemy = enemy;
        nextIteration();

    }

    private void nextIteration() {

        if(user.isAlive() && enemy.isAlive() ){
            System.out.println();
            view.drawUser(user);
            view.drawEnemy(enemy);
            System.out.println();
            show();
        }
    }

    @Override
    public void onUserAttack() {
        System.out.println();
        System.out.println(AsciiHelper.ANSI_YELLOW+"///////////////////////////////////////////"+AsciiHelper.ANSI_RESET);

        view.drawAttack(user, enemy, enemy.isBeatenBy(user));
        view.drawAttack(enemy,user,user.isBeatenBy(enemy));
        System.out.println(AsciiHelper.ANSI_YELLOW+"///////////////////////////////////////////"+AsciiHelper.ANSI_RESET);
        System.out.println();

        nextIteration();

    }

    @Override
    public void onUserDefend() {
        user.defense();
        view.drawAttack(enemy, user, user.isBeatenBy(enemy));
        nextIteration();
    }

    @Override
    public void onDoNothing() {
        view.drawAttack(enemy, user, user.isBeatenBy(enemy));
        nextIteration();
    }
}
