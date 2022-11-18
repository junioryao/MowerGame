package org.publicistsapient.gamelogic;

import org.junit.jupiter.api.Test;
import org.publicistsapient.game.GameSurface;
import org.publicistsapient.game.MowerCoordinate;
import org.publicistsapient.game.MowerGame;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MowerGameTest {

    @Test
    void apply() {
        GameSurface gameSurface = new GameSurface(5, 5);
        MowerCoordinate mowerCoordinate = new MowerCoordinate(1, 2, "N");
        MowerGame mowerGame = MowerGame.builder().gameSurface(gameSurface).mowerCoordinate(mowerCoordinate)
                                       .gameInstructions(List.of("G", "A", "G", "A", "G", "A", "G", "A", "A"))
                                       .build();
        mowerGame.applyInstructions();
        assertArrayEquals(new String[]{"1", "3", "N"}, new String[]{
            mowerGame.getMowerCoordinate().getX().toString(), mowerGame.getMowerCoordinate().getY().toString(),
            mowerGame.getMowerCoordinate().getOrientation()});
    }

    @Test
    void apply1() {

        GameSurface gameSurface = new GameSurface(5, 5);
        MowerCoordinate mowerCoordinate = new MowerCoordinate(3, 3, "E");
        MowerGame mowerGame = MowerGame.builder().gameSurface(gameSurface).mowerCoordinate(mowerCoordinate)
                                       .gameInstructions(List.of("A", "A", "D", "A", "A", "D", "A", "D", "D", "A"))
                                       .build();
        mowerGame.applyInstructions();
        assertArrayEquals(new String[]{"5", "1", "E"}, new String[]{
            mowerGame.getMowerCoordinate().getX().toString(), mowerGame.getMowerCoordinate().getY().toString(),
            mowerGame.getMowerCoordinate().getOrientation()});
    }
}