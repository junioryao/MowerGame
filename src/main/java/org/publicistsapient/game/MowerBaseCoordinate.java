package org.publicistsapient.game;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MowerBaseCoordinate {
    Integer x;
    Integer y;
    String orientation;

    @Override
    public String toString() {
        return "MowerBaseCoordinate{" + "x=" + x + ", y=" + y + ", orientation='" + orientation + '\'' + '}';
    }

    public void moveUp(MowerGame mowerGame) {
        if (getX() <= mowerGame.getGameSurface().x() && getY() + 1 <= mowerGame.getGameSurface().y()) {
            setY(getY() + 1);
        }
    }

    public void moveDown(MowerGame mowerGame) {
        if (getX() <= mowerGame.getGameSurface().x() && getY() - 1 <= mowerGame.getGameSurface().y()) {
            setY(getY() - 1);
        }
    }

    public void moveLeft(MowerGame mowerGame) {
        if (getX() - 1 <= mowerGame.getGameSurface().x() && getY() <= mowerGame.getGameSurface().y()) {
            setX(getX() - 1);
        }
    }

    public void moveRight(MowerGame mowerGame) {
        if (getX() + 1 <= mowerGame.getGameSurface().x() && getY() <= mowerGame.getGameSurface().y()) {
            setX(getX() + 1);
        }
    }
}
