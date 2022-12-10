package org.publicistsapient.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.publicistsapient.constant.Compass;

@Data
@Builder
@AllArgsConstructor
public class MowerCoordinate {
    Integer x;
    Integer y;
    Compass orientation;


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
