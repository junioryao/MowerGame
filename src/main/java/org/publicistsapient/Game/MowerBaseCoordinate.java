package org.publicistsapient.Game;

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
        if (getX() <= mowerGame.gameSurface.x() && getY() + 1 <= mowerGame.gameSurface.y()) {
            setY(getY() + 1);
        }
    }

    public void moveDown(MowerGame mowerGame) {
        if (getX() <= mowerGame.gameSurface.x() && getY() - 1 <= mowerGame.gameSurface.y()) {
            setY(getY() - 1);
        }
    }

    public void moveLeft(MowerGame mowerGame) {
        if (getX() - 1 <= mowerGame.gameSurface.x() && getY() <= mowerGame.gameSurface.y()) {
            setX(getX() - 1);
        }
    }

    public void moveRight(MowerGame mowerGame) {
        if (getX() + 1 <= mowerGame.gameSurface.x() && getY() <= mowerGame.gameSurface.y()) {
            setX(getX() + 1);
        }
    }
}
