package org.game.play;

import org.game.common.mvp.AbstractPresenter;
import org.game.common.mvp.console.AbstractConsoleView;
import org.game.common.mvp.console.ui.utils.AsciiHelper;
import org.game.map.GameMap;
import org.game.map.entities.Entity;


import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

public class GameConsoleView extends AbstractConsoleView implements GameView {


    private static final char CELL_SEPARATOR = '|';

    private static final int MAP_SIZE_MARGIN = 2;




    @Override
    public void draw(GameMap map) {

        String lineSeparator = prepareLineSeparator(map.getEntities().size());
        System.out.println(AsciiHelper.ANSI_RED+"**** Battle Ground****");
        System.out.println(AsciiHelper.ANSI_RED+lineSeparator);
        map.getEntities().forEach(this::drawLine);
        System.out.println(AsciiHelper.ANSI_RED+lineSeparator);
   //     System.out.println(AsciiHelper.ANSI_RESET);
    }

    private String prepareLineSeparator(int size) {
        return generate(()->"-").limit(size+MAP_SIZE_MARGIN).collect(joining());
    }

    private void drawLine(List<Entity> entities) {
        System.out.print(AsciiHelper.ANSI_RED+CELL_SEPARATOR);
        entities.forEach(this::drawEntity);
        System.out.print(AsciiHelper.ANSI_RED+CELL_SEPARATOR);
        System.out.println();
    }

    private void drawEntity(Entity entity) {

        switch (entity.getType()){
            case ROAD:
                entity.getInnerEntity().ifPresent(this::drawEntity);
                if (!entity.containAnotherEntity()) {
                    System.out.print(AsciiHelper.ANSI_YELLOW+"-"+AsciiHelper.ANSI_RESET);
                }
                break;
            case CHARACTER:
                if (entity.isUser()) {
                    System.out.print(AsciiHelper.ANSI_PURPLE+'U'+AsciiHelper.ANSI_RESET);
                    break;
                }
            default:
                System.out.print(AsciiHelper.ANSI_BLACK+entity.getName().charAt(0)+AsciiHelper.ANSI_RESET);
        }
    }

    @Override
    public void showWinnerNotification() {

    }

    @Override
    public void showGameOverNotification() {

    }

    @Override
    public void draw() {
        throw  new UnsupportedOperationException("This method is not supported");
    }
}
