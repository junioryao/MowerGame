package org.publicistsapient.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.publicistsapient.constant.Compass;
import org.publicistsapient.gamelogic.GameSurface;

import java.util.Map;
import java.util.function.Consumer;

import static org.publicistsapient.constant.Compass.E;
import static org.publicistsapient.constant.Compass.N;
import static org.publicistsapient.constant.Compass.S;
import static org.publicistsapient.constant.Compass.W;

@Data
@Builder
@AllArgsConstructor
public class MowerPosition {
    private static Map<Compass, Consumer<MowerPosition>> executor;

    static {
        executor = Map.of(S, MowerPosition::moveDown, N, MowerPosition::moveUp, W, MowerPosition::moveLeft, E, MowerPosition::moveRight);
    }

    Integer x;
    Integer y;
    GameSurface gameSurface;
    Compass orientation;

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

    public void move(Compass compassOrientation) {
        Consumer<MowerPosition> consumer = executor.get(compassOrientation);
        consumer.accept(this);
        setOrientation(compassOrientation);
    }
}
