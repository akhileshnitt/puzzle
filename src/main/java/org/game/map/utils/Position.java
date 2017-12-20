package org.game.map.utils;

public class Position {

    private int left;
    private int top;


    private Position(int left, int top) {
        this.left = left;
        this.top = top;
    }


    public static Position of(int left, int top){
        return new Position(left,top);
    }


    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }


    @Override
    public String toString() {
        return "Position{" +
                "left=" + left +
                ", top=" + top +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position positions = (Position) o;

        if (left != positions.left) return false;
        return top == positions.top;
    }

    @Override
    public int hashCode() {
        int result = left;
        result = 31 * result + top;
        return result;
    }
}
