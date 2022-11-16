package org.publicistsapient.constant;

import org.publicistsapient.Game.Direction;

import java.util.Map;

import static org.publicistsapient.Game.Direction.*;

public class Property {
    public static final String GAME_INPUT_PATTERN_TXT = "src/main/resources/gamePattern/gameInputPattern.txt";
    public static final String GAME_INPUT_PATTERN_2_TXT = "src/main/resources/gamePattern/gameInputPattern2.txt";
    public static final String GAME_INPUT_WRONG_GAME_INSTRUCTION = "src/main/resources/gamePattern/wrongGameInputInstruction.txt";
    public static final String WRONG_GAME_SURFACE = "src/main/resources/gamePattern/wrongGameSurfaceDefinition.txt";
    public static final String WRONG_GAME_BASE_COORDINATE = "src/main/resources/gamePattern/wrongGameBaseCoordinate.txt";
    public static final Map<String, Direction> compassMovingCoordinate = Map.of("N", UP, "E", RIGHT, "S", DOWN, "W", LEFT);
    public static Map<String, Integer> compassIndex = Map.of("N", 0, "E", 1, "S", 2, "W", 3);

}
