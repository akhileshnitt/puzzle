package org.game.map.entities.character;

import org.game.common.mvp.AbstractPresenter;
import org.game.map.entities.Entity;

import static org.game.map.entities.EntityFactory.userCharacter;

public class NewCharacterPresenter extends AbstractPresenter<NewCharacterView> implements NewCharacterFactory, NewCharacterView.ActionDelegate {


    private Race race;
    private Sex sex;
    private String name;

    public NewCharacterPresenter(NewCharacterView view) {
        super(view);
        view.setDelegate(this);
    }

    @Override
    public Entity getGameCharacter() {
        show();
        return userCharacter(name, race, sex);
    }


    @Override
    public void onChosen(Race race) {
        this.race = race;

    }

    @Override
    public void onChosen(Sex sex) {
       this.sex = sex;
    }

    @Override
    public void onChosen(String name) {

        this.name = name;
    }


}
