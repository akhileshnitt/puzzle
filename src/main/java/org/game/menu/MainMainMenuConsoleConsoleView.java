package org.game.menu;

import org.game.common.mvp.console.AbstractConsoleView;
import org.game.common.mvp.console.ui.Menu;



public class MainMainMenuConsoleConsoleView extends AbstractConsoleView<MainMenuView.ActionDelegate> implements MainMenuView {

    private final Menu<MainMenuItem> menu = new Menu<MainMenuItem>("Main Menu",MainMenuItem.values());


    @Override
    public void draw() {
        menu.draw();
        switch (menu.chooseItem()){
            case START:
                delegate.onStartChosen();
                break;
            case RESUME:
                delegate.onResumeChosen();
                break;
            default:

        }

    }


    enum MainMenuItem {
        START("Start new game"),
        RESUME("Resume previous game");

        private final String title;

        MainMenuItem(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
