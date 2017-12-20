package org.game.map.behaviour.user;


import org.game.common.mvp.console.ui.Menu;
import org.game.map.utils.Position;

public class UserMovementConsoleInput implements UserMovementInput {

    private final Menu<Movement>  menu = new Menu<Movement>("Choose Character Movement",Movement.values());


    @Override
    public Position getNextPosition(Position currentPosition) {
       menu.draw();
        Movement movement = menu.chooseItem();
      switch (menu.chooseItem()){
          case UP:
              return Position.of(currentPosition.getLeft(),currentPosition.getTop()-1);
          case DOWN:
              return Position.of(currentPosition.getLeft(),currentPosition.getTop()+1);
          case LEFT:
              return Position.of(currentPosition.getLeft()-1,currentPosition.getTop());
          case RIGHT:
              return Position.of(currentPosition.getLeft()+1,currentPosition.getTop());
          default:
              throw new IllegalStateException("Invalid movement item"+movement);

      }

    }

    private enum Movement{
        UP("Move Up"),
        DOWN("Move Down"),
        LEFT("Move Left"),
        RIGHT("Move Right");


        private final String title;

        Movement(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
