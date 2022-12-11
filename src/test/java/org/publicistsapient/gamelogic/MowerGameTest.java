package org.publicistsapient.gamelogic;

import org.junit.jupiter.api.Test;
import org.publicistsapient.game.Compass;
import org.publicistsapient.game.GameSurface;
import org.publicistsapient.game.MowerGame;
import org.publicistsapient.game.MowerPosition;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.publicistsapient.game.Instruction.getInstructions;

class MowerGameTest {

    @Test
    void apply() {
        GameSurface gameSurface = new GameSurface(5, 5);
        MowerPosition mowerPosition = new MowerPosition(1, 2, gameSurface, Compass.N);
        MowerGame mowerGame = MowerGame.builder()
                                       .mowerPosition(mowerPosition)
                                       .gameInstructions(getInstructions(List.of("G", "A", "G", "A", "G", "A", "G", "A", "A")))
                                       .build();
        mowerGame.applyInstructions();
        assertArrayEquals(new String[]{"1", "3", "N"}, new String[]{mowerGame.getMowerPosition().getX().toString(),
            mowerGame.getMowerPosition().getY().toString(), mowerGame.getMowerPosition().getOrientation().toString()});
    }

    @Test
    void apply1() {

        GameSurface gameSurface = new GameSurface(5, 5);
        MowerPosition mowerPosition = new MowerPosition(3, 3, gameSurface, Compass.E);
        MowerGame mowerGame = MowerGame.builder()
                                       .mowerPosition(mowerPosition)
                                       .gameInstructions(getInstructions(List.of("A", "A", "D", "A", "A", "D", "A", "D", "D", "A")))
                                       .build();
        mowerGame.applyInstructions();
        assertArrayEquals(new String[]{"5", "1", "E"}, new String[]{mowerGame.getMowerPosition().getX().toString(),
            mowerGame.getMowerPosition().getY().toString(), mowerGame.getMowerPosition().getOrientation().toString()});
    }
}