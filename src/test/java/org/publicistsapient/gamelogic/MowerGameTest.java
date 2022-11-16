package org.publicistsapient.gamelogic;

import org.junit.jupiter.api.Test;
import org.publicistsapient.game.GameSurface;
import org.publicistsapient.game.MowerBaseCoordinate;
import org.publicistsapient.game.MowerGame;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MowerGameTest {

    @Test
    void apply() {
        GameSurface gameSurface = new GameSurface(5, 5);
        MowerBaseCoordinate mowerBaseCoordinate = new MowerBaseCoordinate(1, 2, "N");
        MowerGame mowerGame = MowerGame.builder().gameSurface(gameSurface).mowerBaseCoordinate(mowerBaseCoordinate)
                                       .mowerGameInstruction(List.of("G", "A", "G", "A", "G", "A", "G", "A", "A"))
                                       .build();
        mowerGame.applyInstruction();
        assertArrayEquals(new String[]{"1", "3", "N"}, new String[]{
            mowerGame.getMowerBaseCoordinate().getX().toString(), mowerGame.getMowerBaseCoordinate().getY().toString(),
            mowerGame.getMowerBaseCoordinate().getOrientation()});
    }

    @Test
    void apply1() {

        GameSurface gameSurface = new GameSurface(5, 5);
        MowerBaseCoordinate mowerBaseCoordinate = new MowerBaseCoordinate(3, 3, "E");
        MowerGame mowerGame = MowerGame.builder().gameSurface(gameSurface).mowerBaseCoordinate(mowerBaseCoordinate)
                                       .mowerGameInstruction(List.of("A", "A", "D", "A", "A", "D", "A", "D", "D", "A"))
                                       .build();
        mowerGame.applyInstruction();
        assertArrayEquals(new String[]{"5", "1", "E"}, new String[]{
            mowerGame.getMowerBaseCoordinate().getX().toString(), mowerGame.getMowerBaseCoordinate().getY().toString(),
            mowerGame.getMowerBaseCoordinate().getOrientation()});
    }
}