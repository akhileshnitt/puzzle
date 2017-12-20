package org.game.map.task.fight;

import org.game.common.mvp.View;
import org.game.map.entities.Entity;

public interface FightView extends View<FightView.ActionDeletegate>{
    void drawUser(Entity user);
    void drawEnemy(Entity enemy);
    void drawAttack(Entity attacker, Entity defender, int damage);

    public interface ActionDeletegate {
        void onUserAttack();
        void onUserDefend();
        void onDoNothing();
    }
}
