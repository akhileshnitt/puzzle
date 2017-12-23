package org.game.common.mvp.console.ui;


import org.game.common.mvp.console.ui.utils.AsciiHelper;
import org.game.common.mvp.console.ui.utils.ConsoleReader;
import org.game.map.utils.IntRange;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Menu<T extends  Enum> implements Component {

    private static final int MENU_ITEM_OFFSET = 1;

    private static final Function<? super  Enum,String>ENUM_TO_STRING = someEnum -> someEnum.ordinal() + MENU_ITEM_OFFSET + ". " + someEnum;

    private final String title;

    private final T[] items;
    private final IntRange acceptableItems;

    private final ConsoleReader reader = new ConsoleReader();

    public Menu(String title, T... items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("There are no configured menu items");
        }
        this.title = title;
        this.items = items;

        this.acceptableItems = IntRange.of(1, items.length);
    }


    public void draw() {

        System.out.println(AsciiHelper.ANSI_CYAN+title+AsciiHelper.ANSI_RESET);
        Stream.of(items).map(ENUM_TO_STRING).forEach(System.out::println);
    }

    public T chooseItem() {
        printMenuFooter(false);

        return items[readItemIndex()];

    }

    private int readItemIndex() {
        return reader.readIntegerUntil(itemIsInAcceptableRange(), redrawWithWarningMessage) - MENU_ITEM_OFFSET;
    }

    private void printMenuFooter(boolean hasToPrintWarning) {
        if (hasToPrintWarning) {
            System.out.println(AsciiHelper.ANSI_GREEN+"Operation number is incorrect. Please, type correct one."+AsciiHelper.ANSI_RESET);
        }
        System.out.println(AsciiHelper.ANSI_GREEN+"Put operation's number which you want to do:"+AsciiHelper.ANSI_RESET);
    }


    private Predicate<String> itemIsInAcceptableRange() {
        return line -> acceptableItems.contains(Integer.parseInt(line));
    }

    private final Runnable redrawWithWarningMessage = () -> {
        redraw();
        printMenuFooter(true);
    };
}
