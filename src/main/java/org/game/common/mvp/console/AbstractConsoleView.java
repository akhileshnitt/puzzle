package org.game.common.mvp.console;

import org.game.common.mvp.View;

public abstract  class AbstractConsoleView<T> implements View<T> {

    protected T delegate;

    @Override
    public void setDelegate(T delegate) {
        this.delegate = delegate;

    }



    @Override
    public void erase() {

    }
}
