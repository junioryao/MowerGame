package org.publicistsapient.gameLogic;

import org.junit.jupiter.api.Test;
import org.publicistsapient.Game.GameSurface;
import org.publicistsapient.Game.MowerBaseCoordinate;
import org.publicistsapient.Game.MowerGame;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MowerGameTest {

    @Test
    void apply() {
        GameSurface gameSurface = new GameSurface(5, 5);
        MowerBaseCoordinate mowerBaseCoordinate = new MowerBaseCoordinate(1, 2, "N");
        MowerGame mowerGame = MowerGame.builder().gameSurface(gameSurface).mowerBaseCoordinate(mowerBaseCoordinate)
                                       .mowerGameInstruction(new String[]{"G", "A", "G", "A", "G", "A", "G", "A", "A"})
                                       .build();
        mowerGame.applyInstruction();
        assertArrayEquals(new String[]{mowerGame.getMowerBaseCoordinate().getX().toString(),
            mowerGame.getMowerBaseCoordinate().getY().toString(),
            mowerGame.getMowerBaseCoordinate().getOrientation()}, new String[]{"1", "3", "N"});
    }

    @Test
    void apply1() {

        GameSurface gameSurface = new GameSurface(5, 5);
        MowerBaseCoordinate mowerBaseCoordinate = new MowerBaseCoordinate(3, 3, "E");
        MowerGame mowerGame = MowerGame.builder().gameSurface(gameSurface).mowerBaseCoordinate(mowerBaseCoordinate)
                                       .mowerGameInstruction(new String[]{"A", "A", "D", "A", "A", "D", "A", "D", "D",
                                           "A"}).build();
        mowerGame.applyInstruction();
        assertArrayEquals(new String[]{mowerGame.getMowerBaseCoordinate().getX().toString(),
            mowerGame.getMowerBaseCoordinate().getY().toString(),
            mowerGame.getMowerBaseCoordinate().getOrientation()}, new String[]{"5", "1", "E"});
    }
}