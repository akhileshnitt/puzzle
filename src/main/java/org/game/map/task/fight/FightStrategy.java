package org.game.map.task.fight;

import org.game.common.mvp.AbstractPresenter;
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
            view.drawUser(user);
            view.drawEnemy(enemy);
            show();
        }
    }

    @Override
    public void onUserAttack() {

        view.drawAttack(user, enemy, enemy.isBeatenBy(user));
        nextIteration();

    }

    @Override
    public void onUserDefend() {

    }

    @Override
    public void onDoNothing() {

    }
}
