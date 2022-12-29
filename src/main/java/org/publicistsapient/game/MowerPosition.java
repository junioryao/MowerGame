package org.publicistsapient.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.function.Consumer;

import static org.publicistsapient.game.Compass.*;

@Data
@Builder
@AllArgsConstructor
public class MowerPosition implements Movement {
    Integer x;
    Integer y;
    GameSurface gameSurface;
    Compass orientation;

    private static Map<Compass, Consumer<MowerPosition>> executor;

    static {
        executor = Map.of(S, MowerPosition::moveDown, N, MowerPosition::moveUp, W, MowerPosition::moveLeft, E,
                MowerPosition::moveRight);
    }

    @Override
    public String toString() {
        return "MowerPosition {" + "x=" + x + ", y=" + y + ", orientation=" + orientation + '}';
    }

    private void moveUp() {
        if (getX() <= gameSurface.x() && getY() + 1 <= gameSurface.y()) {
            setY(getY() + 1);
        }
    }

    private void moveDown() {
        if (getX() <= gameSurface.x() && getY() - 1 <= gameSurface.y()) {
            setY(getY() - 1);
        }
    }

    private void moveLeft() {
        if (getX() - 1 <= gameSurface.x() && getY() <= gameSurface.y()) {
            setX(getX() - 1);
        }
    }

    private void moveRight() {
        if (getX() + 1 <= gameSurface.x() && getY() <= gameSurface.y()) {
            setX(getX() + 1);
        }
    }

    @Override
    public void execute() {
        executor.get(getOrientation()).accept(this);
    }
}
