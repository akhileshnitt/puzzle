package org.game.common.mvp;

public interface View <T>{

    void setDelegate(T t);
    void draw();
    void erase();
}
