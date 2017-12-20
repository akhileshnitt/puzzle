package org.game.map.entities.character;

import org.game.common.mvp.console.AbstractConsoleView;
import org.game.common.mvp.console.ui.Menu;
import org.game.common.mvp.console.ui.TextBox;

public class NewCharacterConsoleConsoleView  extends AbstractConsoleView<NewCharacterView.ActionDelegate> implements NewCharacterView {

    private final TextBox characterName = new TextBox("Character name:");

    private final Menu<Race> raceMenu = new Menu<>("Choose Race:", Race.values());

    private final Menu<Sex> sexMenu = new Menu<>("Choose Sex:", Sex.values());


    @Override
    public void draw() {
        System.out.println("New Character Menu");

        characterName.draw();
        delegate.onChosen(characterName.getValue());

        raceMenu.draw();
        delegate.onChosen(raceMenu.chooseItem());

        sexMenu.draw();
        delegate.onChosen(sexMenu.chooseItem());

    }
}
