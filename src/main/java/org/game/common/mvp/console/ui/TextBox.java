package org.game.common.mvp.console.ui;

import org.game.common.mvp.console.ui.utils.AsciiHelper;
import org.game.common.mvp.console.ui.utils.ConsoleReader;

public class TextBox implements Component{

    private final ConsoleReader reader;

    private final String title;

    public TextBox(String title) {
        this.reader = new ConsoleReader();
        this.title = title;
    }

    @Override
    public void draw() {
        System.out.println(AsciiHelper.ANSI_CYAN+title+AsciiHelper.ANSI_RESET);
    }

    public String getValue() {
        return reader.readString();
    }
}
