package org.game.menu;

import org.game.common.mvp.View;

public interface MainMenuView extends View<MainMenuView.ActionDelegate>{
    public interface ActionDelegate {
        void onStartChosen();
        void onResumeChosen();


    }
}
