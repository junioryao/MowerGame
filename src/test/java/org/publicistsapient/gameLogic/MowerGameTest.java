package org.publicistsapient.gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MowerGameTest {

    @Test
    void apply() {
        MowerGame mowerGame = new MowerGame("N", new int[]{1, 2}, new String[]{"G", "A", "G", "A", "G", "A", "G", "A",
            "A"}, new int[]{5, 5});
        mowerGame.applyInstruction();
        assertArrayEquals(mowerGame.getMowerBaseCoordinate(), new String[]{"1", "3", "N"});
    }

    @Test
    void apply1() {
        MowerGame mowerGame = new MowerGame("E", new int[]{3, 3}, new String[]{"A", "A", "D", "A", "A", "D", "A", "D",
            "D", "A"}, new int[]{5, 5});
        mowerGame.applyInstruction();
        assertArrayEquals(mowerGame.getMowerBaseCoordinate(), new String[]{"5", "1", "E"});
    }
}