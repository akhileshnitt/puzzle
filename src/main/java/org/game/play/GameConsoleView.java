package org.game.play;

import org.game.common.mvp.AbstractPresenter;
import org.game.common.mvp.console.AbstractConsoleView;
import org.game.map.GameMap;
import org.game.map.entities.Entity;
import sun.plugin.dom.views.AbstractView;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

public class GameConsoleView extends AbstractConsoleView implements GameView {


    private static final char CELL_SEPARATOR = '|';

    private static final int MAP_SIZE_MARGIN = 2;




    @Override
    public void draw(GameMap map) {

        String lineSeparator = prepareLineSeparator(map.getEntities().size());
        System.out.println("**** Battle Ground****");
        System.out.println(lineSeparator);
        map.getEntities().forEach(this::drawLine);
        System.out.println(lineSeparator);
    }

    private String prepareLineSeparator(int size) {
        return generate(()->"-").limit(size+MAP_SIZE_MARGIN).collect(joining());
    }

    private void drawLine(List<Entity> entities) {
        System.out.print(CELL_SEPARATOR);
        entities.forEach(this::drawEntity);
        System.out.print(CELL_SEPARATOR);
        System.out.println();
    }

    private void drawEntity(Entity entity) {

        switch (entity.getType()){
            case ROAD:
                entity.getInnerEntity().ifPresent(this::drawEntity);
                if (!entity.containAnotherEntity()) {
                    System.out.print(' ');
                }
                break;
            case CHARACTER:
                if (entity.isUser()) {
                    System.out.print('U');
                    break;
                }
            default:
                System.out.print(entity.getName().charAt(0));
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
