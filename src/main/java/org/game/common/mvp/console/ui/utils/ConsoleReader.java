package org.game.common.mvp.console.ui.utils;

import java.util.Scanner;

public class ConsoleReader {

    public String readString() {
        return scanner().nextLine();
    }

    private Scanner scanner() {
        return new Scanner(System.in, "UTF-8");
    }

    public int  readIntegerUntil() {

        return Integer.valueOf(readString());
    }
}
