package org.publicistsapient.gamelogic;

import org.publicistsapient.exception.GameValidatorException;

import java.util.List;
import java.util.regex.Pattern;

import static org.publicistsapient.constant.Constant.INVALID_GAME_INSTRUCTION;
import static org.publicistsapient.constant.Constant.MOWER_COORDINATE_CAN_NOT_GET_VALIDATED;
import static org.publicistsapient.constant.Constant.WRONG_GAME_SURFACE_DEFINITION;

class ValidatorUtils {
    public static String[] getMowerCoordinate(String s) {
        String[] coordinate = s.toUpperCase().split(" ");
        if (coordinate.length == 3) return coordinate;
        if (coordinate.length == 2) {
            int x = Integer.parseInt(coordinate[0]);
            int y = Integer.parseInt(coordinate[1]);
            if (y == x + 1) return new String[]{coordinate[0], coordinate[1], "N"};
        }
        throw new GameValidatorException(MOWER_COORDINATE_CAN_NOT_GET_VALIDATED);
    }

    public static String[] getMowerInstructions(String s) {
        String[] instruction = s.toUpperCase().split("");
        for (String s1 : instruction) {
            if (!Pattern.matches("[^BCEFHIJKLMNOPQRSTUVWXYZ]", s1)) {
                throw new GameValidatorException(INVALID_GAME_INSTRUCTION);
            }
        }
        return instruction;
    }

    public static int[] getSurface(List<String> gameLogic) {
        int surfaceRow = 0;
        String[] surface = gameLogic.get(surfaceRow).split(" ");
        if (surface.length != 2) throw new GameValidatorException(WRONG_GAME_SURFACE_DEFINITION);
        int x = Integer.parseInt(surface[0]);
        int y = Integer.parseInt(surface[1]);
        return new int[]{x, y};
    }
}
