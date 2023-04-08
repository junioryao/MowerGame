package org.publicistsapient.game;

import org.publicistsapient.gamelogic.MowerValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.publicistsapient.game.Instruction.getInstructions;

public class MowerGameBuilder implements Builder<MowerGame, String, Integer> {
    private static final Logger LOGGER = Logger.getLogger(MowerGameBuilder.class.getName());
    private final MowerValidator validator = MowerValidator.Instance();

    private GameSurface buildGameSurface(Integer[] surface) {
        return new GameSurface(surface[0], surface[1]);
    }

    private MowerPosition buildMowerCoordinate(String[] mowerCoordinate, GameSurface gameSurface) {
        return MowerPosition.builder()
                .x(Integer.parseInt(mowerCoordinate[0]))
                .y(Integer.parseInt(mowerCoordinate[1]))
                .gameSurface(gameSurface)
                .orientation(Compass.valueOf(mowerCoordinate[2]))
                .build();
    }

    private MowerGame buildGame(String[] mowerInstructions, MowerPosition mowerPosition) {
        return MowerGame.builder()
                .mowerPosition(mowerPosition)
                .gameInstructions(getInstructions(List.of(mowerInstructions)))
                .build()
                .applyInstructions();
    }

    @Override
    public List<MowerGame> buildGames(List<String> gameLogic, Integer[] surface) {
        List<MowerGame> mowerGames = new ArrayList<>();
        GameSurface gameSurface = buildGameSurface(surface);
        for (int i = 0; i < gameLogic.size() - 1; i = i + 2) {
            String[] mowerCoordinate = validator.getMowerCoordinate(gameLogic.get(i));
            String[] mowerInstructions = validator.getMowerInstructions(gameLogic.get(i + 1));
            MowerPosition coordinate = buildMowerCoordinate(mowerCoordinate, gameSurface);
            MowerGame mowerGame = buildGame(mowerInstructions, coordinate);
            mowerGames.add(mowerGame);
        }
        LOGGER.info("Mower game built successfully");
        return mowerGames;
    }
}
