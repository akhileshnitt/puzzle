package org.game.map.entities.character;

import org.game.common.mvp.View;

public interface NewCharacterView extends View<NewCharacterView.ActionDelegate> {


    public interface ActionDelegate {

        void onChosen(Race race);

        void onChosen(Sex sex);

        void onChosen(String name);

    }
}
