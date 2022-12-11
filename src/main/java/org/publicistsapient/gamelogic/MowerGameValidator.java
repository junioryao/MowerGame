package org.publicistsapient.gamelogic;

import org.publicistsapient.fileprocessor.FileProcessor;
import org.publicistsapient.game.Compass;
import org.publicistsapient.game.GameSurface;
import org.publicistsapient.game.MowerGame;
import org.publicistsapient.game.MowerPosition;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.publicistsapient.game.Instruction.getInstructions;

/**
 * @implSpec validate game input data and build object
 */
public class MowerGameValidator implements Validator<MowerGame> {
    private static final Logger LOGGER = Logger.getLogger(MowerGameValidator.class.getName());
    private final FileProcessor fileProcessor;

    public MowerGameValidator(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    private static GameSurface buildGameSurface(int[] surface) {
        return new GameSurface(surface[0], surface[1]);
    }

    private static MowerPosition buildMowerCoordinate(String[] mowerCoordinate, GameSurface gameSurface) {
        return MowerPosition.builder()
                            .x(Integer.parseInt(mowerCoordinate[0]))
                            .y(Integer.parseInt(mowerCoordinate[1]))
                            .gameSurface(gameSurface)
                            .orientation(Compass.valueOf(mowerCoordinate[2]))
                            .build();
    }

    private static MowerGame buildGame(String[] mowerInstructions, MowerPosition mowerPosition) {
        return MowerGame.builder()
                        .mowerPosition(mowerPosition)
                        .gameInstructions(getInstructions(List.of(mowerInstructions)))
                        .build()
                        .applyInstructions();
    }


    @Override
    public List<MowerGame> execute() throws FileNotFoundException {
        int surfaceCoordinate = 0;
        List<String> gameLogic = fileProcessor.buildGameProcess();
        int[] surface = ValidatorUtils.getSurface(gameLogic);
        gameLogic.remove(surfaceCoordinate);
        return buildGames(gameLogic, surface);
    }

    private List<MowerGame> buildGames(List<String> gameLogic, int[] surface) {
        List<MowerGame> mowerGames = new ArrayList<>();
        GameSurface gameSurface = buildGameSurface(surface);
        for (int i = 0; i < gameLogic.size() - 1; i = i + 2) {
            String[] mowerCoordinate = ValidatorUtils.getMowerCoordinate(gameLogic.get(i));
            String[] mowerInstructions = ValidatorUtils.getMowerInstructions(gameLogic.get(i + 1));
            MowerPosition coordinate = buildMowerCoordinate(mowerCoordinate, gameSurface);
            MowerGame mowerGame = buildGame(mowerInstructions, coordinate);
            mowerGames.add(mowerGame);
        }
        LOGGER.info("Mower game build successfully");
        return mowerGames;
    }
}
